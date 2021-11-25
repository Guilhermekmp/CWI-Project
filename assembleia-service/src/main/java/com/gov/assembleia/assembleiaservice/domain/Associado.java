package com.gov.assembleia.assembleiaservice.domain;

import com.gov.assembleia.assembleiaservice.service.enumeration.SimNaoEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "TB_ASSOCIADO")
public class Associado implements Serializable {

  private static final long serialVersionUID = -4477313000855216879L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_ASSOCIADO")
  private Long id;

  @Column(name = "CPF", nullable = false)
  private String cpf;

  @Column(name = "VOTO", nullable = false, length = 1)
  @Enumerated(EnumType.STRING)
  private SimNaoEnum voto;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private SessaoVotacao sessaoVotacao;
}
