package com.gov.assembleia.assembleiaservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SessaoVotacaoDTO implements Serializable {

  private static final long serialVersionUID = 4633047718277637863L;

  private Long id;

  private LocalDateTime horarioAbertura;

  private LocalDateTime horarioTermino;

  @NotNull
  private Long idPauta;

  private List<AssociadoDTO> associados;
}
