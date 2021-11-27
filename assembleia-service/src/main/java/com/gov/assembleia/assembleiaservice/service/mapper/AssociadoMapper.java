package com.gov.assembleia.assembleiaservice.service.mapper;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

  @Mapping(target = "sessaoVotacao.id", source = "idSessaoVotacao")
  Associado toEntity(AssociadoDTO dto);

  @Mapping(target = "idSessaoVotacao", source = "sessaoVotacao.id")
  AssociadoDTO toDTO(Associado entity);
}
