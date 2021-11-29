package com.gov.assembleia.assembleiaservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class PautaDTO implements Serializable {

  private static final long serialVersionUID = -5095161762512228207L;

  private Long id;

  private String nomePauta;

  private LocalDateTime horarioAberturaVotacao;

  private LocalDateTime horarioTerminoVotacao;

  private List<AssociadoDTO> associados;

}
