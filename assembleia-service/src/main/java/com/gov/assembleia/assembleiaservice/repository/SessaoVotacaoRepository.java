package com.gov.assembleia.assembleiaservice.repository;

import com.gov.assembleia.assembleiaservice.domain.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

  @Query("SELECT COUNT(a) FROM SessaoVotacao sv INNER JOIN sv.associados a WHERE a.voto = 'N'")
  Long buscarTotalVotosNao(@Param("idSessao") Long idSessao);

  @Query("SELECT COUNT(a) FROM SessaoVotacao sv INNER JOIN sv.associados a WHERE a.voto = 'S'")
  Long buscarTotalVotosSim(@Param("idSessao") Long idSessao);

  @Query("SELECT CASE WHEN COUNT(sv) > 0 THEN true ELSE false END FROM SessaoVotacao sv WHERE sv.pauta.id = :idPauta AND sv.horarioTermino > CURRENT_TIMESTAMP ")
  Boolean temSessaoAbertaPorIdPauta(@Param("idPauta") Long idPauta);
}
