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
import org.springframework.web.bind.annotation.RestController;

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
}
