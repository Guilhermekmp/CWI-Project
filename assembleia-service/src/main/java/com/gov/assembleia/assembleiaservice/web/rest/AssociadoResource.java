package com.gov.assembleia.assembleiaservice.web.rest;

import com.gov.assembleia.assembleiaservice.service.AssociadoService;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import com.gov.assembleia.assembleiaservice.service.dto.StatusVotoDTO;
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

  @GetMapping("/{cpf}/{idPauta}")
  public ResponseEntity<StatusVotoDTO> buscarVotosPorId(@PathVariable("cpf") String cpf, @PathVariable("idPauta") Long idPauta){
    log.debug("Requisição REST request para buscar o status de voto do Associado pelo cpf:", cpf);
    StatusVotoDTO statusVotoDTO = service.buscarStatusAssociado(cpf,idPauta);
    return ResponseEntity.ok(statusVotoDTO);
  }
}
