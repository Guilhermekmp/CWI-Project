package com.gov.assembleia.assembleiaservice.service.mapper;

import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import com.gov.assembleia.assembleiaservice.service.dto.SessaoVotacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessaoVotacaoMapper {

  @Mapping(target = "pauta.id", source = "idPauta")
  SessaoVotacao toEntity(SessaoVotacaoDTO dto);

  @Mapping(target = "idPauta", source = "pauta.id")
  SessaoVotacaoDTO toDTO(SessaoVotacao entity);
}
