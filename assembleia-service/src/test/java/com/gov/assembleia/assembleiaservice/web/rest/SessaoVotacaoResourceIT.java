package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.builder.SessaoVotacaoBuilder;
import com.gov.assembleia.assembleiaservice.config.AbstractTestIT;
import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import com.gov.assembleia.assembleiaservice.service.SessaoVotacaoService;
import com.gov.assembleia.assembleiaservice.service.dto.SessaoVotacaoDTO;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@Slf4j
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessaoVotacaoResourceIT extends AbstractTestIT<SessaoVotacaoResource> {

  private static final String API = "/api/sessoes-votacao/";
  private static final String API_ABRIR_VOTACAO = API + "abrir-votacao/";
  private static final String API_CONTAR_VOTOS = API + "contar-votos/";

  @Autowired
  private SessaoVotacaoService service;

  @Autowired
  private SessaoVotacaoBuilder builder;

  @BeforeEach
  public void init(){ super.init(new SessaoVotacaoResource(service));}

  @Test
  @DisplayName("Salvar sessaoVotacao com sucesso")
  @SneakyThrows
  public void salvarSessaoVotacao(){

    SessaoVotacaoDTO dto = builder.construirEntidadeDTO();

    getMockMvc().perform(post(API_ABRIR_VOTACAO)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Salvar sessaoVotacao com sessao ainda aberta")
  @SneakyThrows
  public void salvarSessaoComSessaoAberta(){

    SessaoVotacao sessaoVotacao = builder.construir();
    SessaoVotacaoDTO dto = builder.construirEntidadeDTO();
    dto.setIdPauta(sessaoVotacao.getPauta().getId());

    getMockMvc().perform(post(API_ABRIR_VOTACAO)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Salvar sessaoVotacao com data Termino passada")
  @SneakyThrows
  public void salvarResponsavelDataTerminoPassada(){

    SessaoVotacaoDTO dto = builder.construirEntidadeDTO();
    dto.setHorarioTermino(LocalDateTime.now());

    getMockMvc().perform(post(API_ABRIR_VOTACAO)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Buscar votos de sessaoVotacao por id ")
  @SneakyThrows
  public void buscarPorId(){
    SessaoVotacao sessaoVotacao = builder.construir();

    getMockMvc().perform(get(API_CONTAR_VOTOS + sessaoVotacao.getId()))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Buscar votos de sessaoVotacao por id inexistente")
  @SneakyThrows
  public void buscarPorIdInexistente(){
    getMockMvc().perform(get(API_CONTAR_VOTOS + TestConstantsUtil.ID_INEXISTENTE))
            .andExpect(status().isNotFound());
  }
}
