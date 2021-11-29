package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.service.PautaService;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pautas")
@RequiredArgsConstructor
@Slf4j
public class PautaResource {

  private final PautaService service;

  @GetMapping("/{id}")
  public ResponseEntity<PautaDTO> buscarPorId(@PathVariable Long id){
    log.debug("Requisição REST request para buscar a Pauta por id");
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  @PostMapping()
  public ResponseEntity<PautaDTO> salvar(@RequestBody PautaDTO pautaDTO){
    log.debug("Requisição REST request para salvar Pauta:", pautaDTO);
    return ResponseEntity.ok(service.salvar(pautaDTO));
  }

  @GetMapping("/abrir-votacao/{id}")
  public ResponseEntity<PautaDTO> salvarAberturaSessao(@PathVariable Long id,
                                                       @RequestParam(value = "horarioTermino", required = false) LocalDateTime horarioTerminoVotacao){
    log.debug("Requisição REST request para salvar a abertura de uma Sessao de Votacao na pauta com id:", id);
    return ResponseEntity.ok(service.abrirSessaoVotacao(id,horarioTerminoVotacao));
  }

  @GetMapping("/contar-votos/{id}")
  public ResponseEntity<String> buscarVotosPorId(@PathVariable Long id){
    log.debug("Requisição REST request para buscar a quantidade de votos na SessaoVotacao por id");
    return ResponseEntity.ok(service.buscarQuantidadeVotos(id));
  }
}
