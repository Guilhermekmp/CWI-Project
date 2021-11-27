package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.builder.PautaBuilder;
import com.gov.assembleia.assembleiaservice.config.AbstractTestIT;
import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.service.PautaService;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.util.TestConstantsUtil;
import com.gov.assembleia.assembleiaservice.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
public class PautaResourceIT extends AbstractTestIT<PautaResource> {

  private static final String API = "/api/pautas/";

  @Autowired
  private PautaService service;

  @Autowired
  private PautaBuilder builder;

  @BeforeEach
  public void init(){ super.init(new PautaResource(service));}

  @Test
  @DisplayName("Salvar pauta com sucesso")
  @SneakyThrows
  public void salvarPauta(){

    PautaDTO dto = builder.construirEntidadeDTO();

    getMockMvc().perform(post(API)
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(dto)))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Buscar pauta Por id ")
  @SneakyThrows
  public void buscarPorId(){
    Pauta pauta = builder.construir();

    getMockMvc().perform(get(API + pauta.getId()))
            .andExpect(jsonPath("$.nomePauta").value(pauta.getNomePauta()))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Buscar pauta Por id inexistente")
  @SneakyThrows
  public void buscarPorIdInexistente(){
    getMockMvc().perform(get(API + TestConstantsUtil.ID_INEXISTENTE))
            .andExpect(status().isNotFound());
  }
}
