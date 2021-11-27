package com.gov.assembleia.assembleiaservice.service.dto;

import com.gov.assembleia.assembleiaservice.service.enumeration.SimNaoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class AssociadoDTO implements Serializable {

  private static final long serialVersionUID = 2971439667706705666L;

  private Long id;

  @NotBlank
  private String cpf;

  private SimNaoEnum voto;

  @NotNull
  private Long idSessaoVotacao;
}
