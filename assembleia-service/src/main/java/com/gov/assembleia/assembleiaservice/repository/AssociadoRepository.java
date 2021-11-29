package com.gov.assembleia.assembleiaservice.repository;

import com.gov.assembleia.assembleiaservice.domain.Associado;
import com.gov.assembleia.assembleiaservice.service.dto.AssociadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

  @Query("SELECT CASE WHEN count(a) > 0 THEN true ELSE false END FROM Associado a WHERE a.cpf = :#{#associado.cpf} AND a.pauta.id = :#{#associado.idPauta}")
  Boolean verificarStatusVotoAssociado(@Param("associado") AssociadoDTO dto);
}
