package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService {

  private final PautaRepository repository;
}
