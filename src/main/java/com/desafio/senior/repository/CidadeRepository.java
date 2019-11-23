package com.desafio.senior.repository;

import java.util.List;
import java.util.Optional;

import com.desafio.senior.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Vinicius Silveira
 * 
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>, JpaSpecificationExecutor<Cidade> {

  Optional<Cidade> findByIbgeId(int ibgeId);

  List<Cidade> findByCapitalTrueOrderByName();

  @Query(value = "SELECT uf, COUNT(idcidade) AS count" + " FROM cidade " + " GROUP BY uf "
      + " ORDER BY count DESC", nativeQuery = true)
  List<Object[]> retornaCountCidadesPorUf();

  List<Cidade> findAllByUf(@Param(value = "uf") String uf);

  @Query(value = "SELECT COUNT(DISTINCT(:coluna)) FROM cidade", nativeQuery = true)
  int countRegistrosPorColuna(@Param(value = "coluna") String coluna);

  @Query("SELECT COUNT(c) FROM Cidade AS c")
  public int countCidades();
}