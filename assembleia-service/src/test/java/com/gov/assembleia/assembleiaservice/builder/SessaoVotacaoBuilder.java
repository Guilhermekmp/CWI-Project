package com.gov.assembleia.assembleiaservice.builder;

import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import com.gov.assembleia.assembleiaservice.service.SessaoVotacaoService;
import com.gov.assembleia.assembleiaservice.service.dto.SessaoVotacaoDTO;
import com.gov.assembleia.assembleiaservice.service.mapper.SessaoVotacaoMapper;
import com.gov.assembleia.assembleiaservice.util.TestConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoVotacaoBuilder extends BuilderPadrao<SessaoVotacao,SessaoVotacaoDTO>{

  @Autowired
  private SessaoVotacaoService service;

  @Autowired
  private SessaoVotacaoMapper mapper;

  @Autowired
  private PautaBuilder pautaBuilder;

  @Override
  public SessaoVotacao construirEntidade() {
    SessaoVotacao sessaoVotacao = new SessaoVotacao();
    sessaoVotacao.setPauta(pautaBuilder.construir());
    return sessaoVotacao;
  }

  @Override
  public SessaoVotacaoDTO construirEntidadeDTO() {
    return mapper.toDTO(construirEntidade());
  }

  @Override
  protected SessaoVotacao persistir(SessaoVotacao entidade) {
    SessaoVotacaoDTO dto = service.salvar(mapper.toDTO(entidade));
    return mapper.toEntity(dto);
  }
}
