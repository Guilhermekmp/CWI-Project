package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.service.SessaoVotacaoService;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.dto.SessaoVotacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessoes-votacao")
@RequiredArgsConstructor
@Slf4j
public class SessaoVotacaoResource {

  private final SessaoVotacaoService service;

  @PostMapping("/abrir-votacao")
  public ResponseEntity<SessaoVotacaoDTO> salvarAberturaSessao(@RequestBody SessaoVotacaoDTO sessaoVotacaoDTO){
    log.debug("Requisição REST request para salvar a abertura de uma Sessao de Votacao:", sessaoVotacaoDTO);
    return ResponseEntity.ok(service.salvar(sessaoVotacaoDTO));
  }

  @GetMapping("/contar-votos/{id}")
  public ResponseEntity<String> buscarVotosPorId(@PathVariable Long id){
    log.debug("Requisição REST request para buscar a quantidade de votos na SessaoVotacao por id");
    return ResponseEntity.ok(service.buscarQuantidadeVotos(id));
  }
}
