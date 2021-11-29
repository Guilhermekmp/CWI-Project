package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.repository.AssociadoRepository;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.exception.BadRequestException;
import com.gov.assembleia.assembleiaservice.service.exception.NotFoundException;
import com.gov.assembleia.assembleiaservice.service.mapper.AssociadoMapper;
import com.gov.assembleia.assembleiaservice.service.util.ConstantsUtil;
import com.gov.assembleia.assembleiaservice.service.util.ValidadorCPFUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AssociadoService {

  private final AssociadoRepository repository;

  private final AssociadoMapper mapper;

  private final PautaService pautaService;

  public AssociadoDTO salvar(AssociadoDTO dto){
    if(Boolean.TRUE.equals(verificarAssociadoJaVotou(dto))){
      throw new BadRequestException(ConstantsUtil.ERRO_CPF_JA_VOTOU);
    }
    PautaDTO pautaDTO = pautaService.buscarPorId(dto.getIdPauta());
    if(pautaDTO.getHorarioTerminoVotacao().isBefore(LocalDateTime.now())){
      throw new BadRequestException(ConstantsUtil.ERRO_VOTACAO_ENCERRADA);
    }
    Associado associadoSalvado = repository.save(mapper.toEntity(dto));
    return mapper.toDTO(associadoSalvado);
  }

  public Boolean verificarAssociadoJaVotou(AssociadoDTO dto){
    if(!ValidadorCPFUtil.ehCPFValido(dto.getCpf())){
      throw new NotFoundException(ConstantsUtil.ERRO_CPF_INVALIDO);
    }
    return repository.verificarStatusVotoAssociado(dto);
  }
}
