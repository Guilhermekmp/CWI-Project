package com.gov.assembleia.assembleiaservice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_SESSAO_VOTACAO")
@Data
public class SessaoVotacao implements Serializable {

  private static final long serialVersionUID = -7505974054060449559L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SESSAO_VOT_SEQ")
  @SequenceGenerator(name = "SESSAO_VOT_SEQ", sequenceName = "SESSAO_VOT_SEQ", initialValue = 1, allocationSize=1)
  @Column(name = "ID_SESSAO_VOTACAO")
  private Long id;

  @Column(name = "HORARIO_ABERTURA", nullable = false)
  private LocalDateTime horarioAbertura;

  @Column(name = "HORARIO_TERMINO", nullable = false)
  private LocalDateTime horarioTermino;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Pauta pauta;

  @OneToMany(fetch = FetchType.LAZY,mappedBy = "sessaoVotacao", orphanRemoval = true)
  private List<Associado> associados;
}
