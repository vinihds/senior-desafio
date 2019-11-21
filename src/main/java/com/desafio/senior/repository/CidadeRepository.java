package com.desafio.senior.repository;

import com.desafio.senior.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vinicius Silveira
 * 
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}