package com.gov.assembleia.assembleiaservice.repository;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
