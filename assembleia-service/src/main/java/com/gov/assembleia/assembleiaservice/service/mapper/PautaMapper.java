package com.gov.assembleia.assembleiaservice.service.mapper;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SessaoVotacaoMapper.class)
public interface PautaMapper {

  Pauta toEntity(PautaDTO dto);

  PautaDTO toDTO(Pauta entity);
}
