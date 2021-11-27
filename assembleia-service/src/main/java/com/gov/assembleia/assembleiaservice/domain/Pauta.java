package com.gov.assembleia.assembleiaservice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "TB_PAUTA")
public class Pauta implements Serializable {

  private static final long serialVersionUID = -6209994590025554494L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAUTA_SEQ")
  @SequenceGenerator(name = "PAUTA_SEQ", sequenceName = "PAUTA_SEQ", initialValue = 1, allocationSize=1)
  @Column(name = "ID_PAUTA")
  private Long id;

  @Column(name = "NOME_PAUTA", length = 200, nullable = false)
  private String nomePauta;

  @OneToMany(fetch = FetchType.LAZY,mappedBy = "pauta")
  private List<SessaoVotacao> sessaoVotacaos;
}
