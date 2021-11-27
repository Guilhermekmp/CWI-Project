package com.gov.assembleia.assembleiaservice.builder;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.service.PautaService;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.mapper.PautaMapper;
import com.gov.assembleia.assembleiaservice.util.TestConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PautaBuilder extends BuilderPadrao<Pauta,PautaDTO>{

  @Autowired
  private PautaService service;

  @Autowired
  private PautaMapper mapper;

  @Override
  public Pauta construirEntidade() {
    Pauta pauta = new Pauta();
    pauta.setNomePauta(TestConstantsUtil.NOME_PADRAO);
    return pauta;
  }

  @Override
  public PautaDTO construirEntidadeDTO() {
    return mapper.toDTO(construirEntidade());
  }

  @Override
  protected Pauta persistir(Pauta entidade) {
    PautaDTO dto = service.salvar(mapper.toDTO(entidade));
    return mapper.toEntity(dto);
  }
}
