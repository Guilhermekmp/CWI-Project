package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import com.gov.assembleia.assembleiaservice.repository.PautaRepository;
import com.gov.assembleia.assembleiaservice.service.dto.PautaDTO;
import com.gov.assembleia.assembleiaservice.service.exception.BadRequestException;
import com.gov.assembleia.assembleiaservice.service.exception.NotFoundException;
import com.gov.assembleia.assembleiaservice.service.mapper.PautaMapper;
import com.gov.assembleia.assembleiaservice.service.util.ConstantsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

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

  public PautaDTO abrirSessaoVotacao(Long idPauta, LocalDateTime horarioTerminoVotacao){
    PautaDTO dto = buscarPorId(idPauta);
    if(Boolean.TRUE.equals(verificarPautaVotada(dto.getId()))){
      throw new BadRequestException(ConstantsUtil.ERRO_PAUTA_VOTADA);
    }
    dto.setHorarioAberturaVotacao(LocalDateTime.now());
    dto.setHorarioTerminoVotacao(horarioTerminoVotacao);
    if(Objects.isNull(dto.getHorarioTerminoVotacao())){
      dto.setHorarioTerminoVotacao(dto.getHorarioAberturaVotacao().plusMinutes(ConstantsUtil.VALOR_PADRAO_DATA_TERMINO));
    }
    return processarAberturaVotacao(dto);
  }

  private Boolean verificarPautaVotada(Long id){
    return repository.verificarPautaVotada(id);
  }

  private PautaDTO processarAberturaVotacao(PautaDTO dto){
    if(dto.getHorarioTerminoVotacao().isBefore(dto.getHorarioAberturaVotacao())){
      throw new BadRequestException(ConstantsUtil.ERRO_DATA_TERMINO_PASSADO);
    }
    Pauta pautaSalvada = repository.save(mapper.toEntity(dto));
    return mapper.toDTO(pautaSalvada);
  }

  public String buscarQuantidadeVotos(Long id){
    buscarPorId(id);
    Long totalVotosSim = repository.buscarTotalVotosSim(id);
    Long totalVotosNao = repository.buscarTotalVotosNao(id);
    Long totalVotos = totalVotosNao + totalVotosSim;
    return montarMensagemTotalVotos(totalVotosSim, totalVotosNao, totalVotos);
  }

  private String montarMensagemTotalVotos(Long totalVotosSim, Long totalVotosNao, Long totalVotos) {
    String contagemVotos = ConstantsUtil.MENSAGEM_CONTAGEM_VOTOS;
    contagemVotos = contagemVotos.replace(ConstantsUtil.TOTAL_VOTOS, totalVotos.toString());
    contagemVotos = contagemVotos.replace(ConstantsUtil.TOTAL_VOTOS_SIM, totalVotosSim.toString());
    contagemVotos = contagemVotos.replace(ConstantsUtil.TOTAL_VOTOS_NAO, totalVotosNao.toString());
    return contagemVotos;
  }
}
