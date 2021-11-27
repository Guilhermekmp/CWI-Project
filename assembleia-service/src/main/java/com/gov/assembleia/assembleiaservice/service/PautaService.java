package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.repository.PautaRepository;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.exception.NotFoundException;
import com.gov.assembleia.assembleiaservice.service.mapper.PautaMapper;
import com.gov.assembleia.assembleiaservice.service.util.ConstantsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService {

  private final PautaRepository repository;
  private final PautaMapper mapper;

  public PautaDTO salvar(PautaDTO dto){
    Pauta pautaSalvada = repository.save(mapper.toEntity(dto));
    return mapper.toDTO(pautaSalvada);
  }

  public PautaDTO buscarPorId(Long id){
    return mapper.toDTO(repository.findById(id)
            .orElseThrow(() -> new NotFoundException(ConstantsUtil.ERRO_PAUTA_NAO_ENCONTRADA)));
  }
}
