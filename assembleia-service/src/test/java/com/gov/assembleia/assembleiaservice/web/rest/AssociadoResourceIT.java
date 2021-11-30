package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.builder.AssociadoBuilder;
import com.gov.assembleia.assembleiaservice.config.AbstractTestIT;
import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.service.AssociadoService;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import com.gov.assembleia.assembleiaservice.util.TestConstantsUtil;
import com.gov.assembleia.assembleiaservice.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@Slf4j
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssociadoResourceIT extends AbstractTestIT<AssociadoResource> {

  private static final String API = "/api/users/";
  private static final String API_VOTAR = API + "votar/";

  @Autowired
  private AssociadoService service;

  @Autowired
  private AssociadoBuilder builder;

  @BeforeEach
  public void init(){ super.init(new AssociadoResource(service));}

  @Test
  @DisplayName("Salvar voto de associado com sucesso")
  @SneakyThrows
  public void salvarVotoAssociado(){

    AssociadoDTO dto = builder.construirEntidadeDTO();

    getMockMvc().perform(post(API_VOTAR)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Salvar voto de associado com cpf invalido")
  @SneakyThrows
  public void salvarVotoAssociadoComCPFInvalido(){

    AssociadoDTO dto = builder.construirEntidadeDTO();
    dto.setCpf(TestConstantsUtil.CPF_INVALIDO);

    getMockMvc().perform(post(API_VOTAR)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Salvar voto dupiicado de associado")
  @SneakyThrows
  public void salvarVotoAssociadoDuplicado(){
    Associado associado = builder.construir();
    AssociadoDTO dto = builder.construirEntidadeDTO();
    dto.setIdPauta(associado.getPauta().getId());

    getMockMvc().perform(post(API_VOTAR)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Salvar voto de associado com sessão terminada")
  @SneakyThrows
  public void salvarVotoAssociadoComSessaoTerminada(){

    AssociadoDTO dto = builder.construirEntidadeDTO();
    Thread.sleep(2000);

    getMockMvc().perform(post(API_VOTAR)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Buscar Status de voto do Associado pelo CPF")
  @SneakyThrows
  public void buscarStatusAssociado(){

    AssociadoDTO dto = builder.construirEntidadeDTO();

    getMockMvc().perform(get(API + dto.getCpf() + "/" + dto.getIdPauta()))
            .andExpect(status().isOk());
  }

}
