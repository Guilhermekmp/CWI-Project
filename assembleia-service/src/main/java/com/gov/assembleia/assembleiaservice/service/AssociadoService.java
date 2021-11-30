package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.repository.AssociadoRepository;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.dto.StatusVotoDTO;
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
    if(Boolean.TRUE.equals(verificarDisponibilidadeDeVoto(dto))){
      throw new BadRequestException(ConstantsUtil.ERRO_CPF_JA_VOTOU);
    }
    Associado associadoSalvado = repository.save(mapper.toEntity(dto));
    return mapper.toDTO(associadoSalvado);
  }

  private Boolean verificarDisponibilidadeDeVoto(AssociadoDTO dto) {
    PautaDTO pautaDTO = pautaService.buscarPorId(dto.getIdPauta());
    if(pautaDTO.getHorarioTerminoVotacao().isBefore(LocalDateTime.now())){
      throw new BadRequestException(ConstantsUtil.ERRO_VOTACAO_ENCERRADA);
    }
    return verificarAssociadoJaVotou(dto);
  }

  private Boolean verificarAssociadoJaVotou(AssociadoDTO dto){
    if(!ValidadorCPFUtil.ehCPFValido(dto.getCpf())){
      throw new NotFoundException(ConstantsUtil.ERRO_CPF_INVALIDO);
    }
    return repository.verificarStatusVotoAssociado(dto);
  }

  public StatusVotoDTO buscarStatusAssociado(String cpf, Long idPauta){
    AssociadoDTO associadoDTO = new AssociadoDTO();
    associadoDTO.setCpf(cpf);
    associadoDTO.setIdPauta(idPauta);
    return verificarAssociadoJaVotou(associadoDTO) ? new StatusVotoDTO(ConstantsUtil.ASSOCIADO_NAO_PODE_VOTAR) : new StatusVotoDTO(ConstantsUtil.ASSOCIADO_PODE_VOTAR);
  }
}
