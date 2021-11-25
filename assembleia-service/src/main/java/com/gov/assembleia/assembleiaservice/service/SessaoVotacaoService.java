package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

  private final SessaoVotacaoRepository repository;
}
