package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import com.gov.assembleia.assembleiaservice.repository.SessaoVotacaoRepository;
import com.gov.assembleia.assembleiaservice.service.dto.SessaoVotacaoDTO;
import com.gov.assembleia.assembleiaservice.service.exception.BadRequestException;
import com.gov.assembleia.assembleiaservice.service.exception.NotFoundException;
import com.gov.assembleia.assembleiaservice.service.mapper.SessaoVotacaoMapper;
import com.gov.assembleia.assembleiaservice.service.util.ConstantsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

  private final SessaoVotacaoRepository repository;

  private final SessaoVotacaoMapper mapper;

  private final PautaService pautaService;

  public SessaoVotacaoDTO salvar(SessaoVotacaoDTO dto){
    pautaService.buscarPorId(dto.getIdPauta());
    if(Boolean.TRUE.equals(verificarSessoesAbertasPorIdPauta(dto.getIdPauta()))){
      throw new BadRequestException(ConstantsUtil.ERRO_SESSAO_ABERTA);
    }
    dto.setHorarioAbertura(LocalDateTime.now());
    if(Objects.isNull(dto.getHorarioTermino())){
      dto.setHorarioTermino(dto.getHorarioAbertura().plusMinutes(ConstantsUtil.VALOR_PADRAO_DATA_TERMINO));
    }
    return processarSalvamento(dto);
  }

  private SessaoVotacaoDTO processarSalvamento(SessaoVotacaoDTO dto){
    if(dto.getHorarioTermino().isBefore(dto.getHorarioAbertura())){
      throw new BadRequestException(ConstantsUtil.ERRO_DATA_TERMINO_PASSADO);
    }
    SessaoVotacao sessaoSalvada = repository.save(mapper.toEntity(dto));
    return mapper.toDTO(sessaoSalvada);
  }

  public String buscarQuantidadeVotos(Long id){
    buscarSessaoPorId(id);
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

  public SessaoVotacaoDTO buscarSessaoPorId(Long id){
    return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException(ConstantsUtil.ERRO_SESSAO_NAO_ENCONTRADA)));
  }

  public Boolean verificarSessoesAbertasPorIdPauta(Long idPauta){
    return repository.temSessaoAbertaPorIdPauta(idPauta);
  }
}
