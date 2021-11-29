package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.service.AssociadoService;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class AssociadoResource {

  private final AssociadoService service;

  @PostMapping("/votar")
  public ResponseEntity<AssociadoDTO> salvarAberturaSessao(@RequestBody AssociadoDTO associadoDTO){
    log.debug("Requisição REST request para salvar o voto do Associado:", associadoDTO);
    return ResponseEntity.ok(service.salvar(associadoDTO));
  }
}
