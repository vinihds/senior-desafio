package com.desafio.senior.repository;

import java.util.List;
import java.util.Optional;

import com.desafio.senior.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vinicius Silveira
 * 
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

  Optional<Cidade> findByIbgeId(int ibgeId);

  List<Cidade> findByCapitalTrueOrderByName();
}