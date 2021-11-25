package com.gov.assembleia.assembleiaservice.repository;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
