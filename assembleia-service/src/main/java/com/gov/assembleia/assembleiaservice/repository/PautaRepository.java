package com.gov.assembleia.assembleiaservice.repository;

import com.gov.assembleia.assembleiaservice.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

  @Query("SELECT COUNT(a) FROM Pauta p INNER JOIN p.associados a WHERE a.voto = 'N' AND p.id = :idPauta")
  Long buscarTotalVotosNao(@Param("idPauta") Long idPauta);

  @Query("SELECT COUNT(a) FROM Pauta p INNER JOIN p.associados a WHERE a.voto = 'S' AND p.id = :idPauta")
  Long buscarTotalVotosSim(@Param("idPauta") Long idPauta);

  @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pauta p WHERE p.id = :idPauta AND p.horarioAberturaVotacao IS NOT NULL")
  Boolean verificarPautaVotada(@Param("idPauta") Long idPauta);
}
