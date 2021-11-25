package com.gov.assembleia.assembleiaservice.service.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SimNaoEnum {

  S("Sim"), N("Não");

  private String valor;
}
