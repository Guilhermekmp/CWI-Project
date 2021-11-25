package com.gov.assembleia.assembleiaservice.service;

import com.gov.assembleia.assembleiaservice.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssociadoService {

  private final AssociadoRepository repository;
}
