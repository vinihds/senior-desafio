package com.desafio.senior.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.desafio.senior.model.Cidade;
import com.desafio.senior.repository.CidadeRepository;
import com.desafio.senior.util.SetupCSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

  @Autowired
  CidadeRepository cidadeRepository;
  @Autowired
  SetupCSV setup;

  @PersistenceContext
  protected EntityManager em;

  private String CIDADES_FILE = ".\\csv\\Desafio Cidades - Back End.csv";

  public void setupCidades() {
    List<Cidade> cidadeList = setup.loadObjectList(Cidade.class, CIDADES_FILE);

    for (Cidade cidade : cidadeList) {
      setupCidade(cidade);
    }
  }

  private void setupCidade(Cidade cidade) {
    Optional<Cidade> cidadeOld = cidadeRepository.findByIbgeId(cidade.getIbgeId());

    if (!cidadeOld.isPresent()) {
      cidadeRepository.save(cidade);
    }
  }

  public long countRegistrosPorColuna(String attributeName) {
    CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    cq.select(qb.countDistinct(cq.from(Cidade.class).get(attributeName)));

    return em.createQuery(cq).getSingleResult();
  }
}