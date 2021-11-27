package com.gov.assembleia.assembleiaservice.builder;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import com.gov.assembleia.assembleiaservice.service.AssociadoService;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import com.gov.assembleia.assembleiaservice.service.enumeration.SimNaoEnum;
import com.gov.assembleia.assembleiaservice.service.mapper.AssociadoMapper;
import com.gov.assembleia.assembleiaservice.util.TestConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssociadoBuilder extends BuilderPadrao<Associado,AssociadoDTO>{

  @Autowired
  private AssociadoService service;

  @Autowired
  private AssociadoMapper mapper;

  @Autowired
  private SessaoVotacaoBuilder sessaoVotacaoBuilder;

  @Override
  public Associado construirEntidade() {
    Associado associado = new Associado();
    associado.setCpf(TestConstantsUtil.CPF_VALIDO);
    associado.setVoto(SimNaoEnum.S);
    associado.setSessaoVotacao(sessaoVotacaoBuilder.construir());
    return associado;
  }

  @Override
  public AssociadoDTO construirEntidadeDTO() {
    return mapper.toDTO(construirEntidade());
  }

  public AssociadoDTO construirEntidadeDTOComSessaoCurta() {
    SessaoVotacao sessaoVotacao = sessaoVotacaoBuilder.construirEntidade();
    sessaoVotacao.setHorarioTermino(LocalDateTime.now().plusSeconds(2));
    Associado associado = new Associado();
    associado.setCpf(TestConstantsUtil.CPF_VALIDO);
    associado.setVoto(SimNaoEnum.S);
    associado.setSessaoVotacao(sessaoVotacaoBuilder.persistir(sessaoVotacao));
    return mapper.toDTO(associado);
  }

  @Override
  protected Associado persistir(Associado entidade) {
    AssociadoDTO dto = service.salvar(mapper.toDTO(entidade));
    return mapper.toEntity(dto);
  }
}
