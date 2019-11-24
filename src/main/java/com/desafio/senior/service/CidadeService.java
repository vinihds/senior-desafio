package com.desafio.senior.service;

import java.text.DecimalFormat;
import java.util.Arrays;
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

  private DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");

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

  public List<?> distanciaEntreCidades() {
    List<Cidade> cidades = cidadeRepository.findAll();
    double distanciaTemp = 0;
    double maxDistancia = 0;
    Cidade cidadeDistante1 = new Cidade();
    Cidade cidadeDistante2 = new Cidade();

    for (Cidade cidade1 : cidades) {
      for (Cidade cidade2 : cidades.subList((cidades.indexOf(cidade1) + 1), cidades.size())) {
        distanciaTemp = calcularDistancia(cidade1, cidade2);

        if (distanciaTemp > maxDistancia) {
          maxDistancia = distanciaTemp;
          cidadeDistante1 = cidade1;
          cidadeDistante2 = cidade2;
        }
      }
    }

    return Arrays.asList(cidadeDistante1, cidadeDistante2,
        "A maior distancia entre as cidades é de " + decimalFormat.format(maxDistancia) + "km");
  }

  private double calcularDistancia(Cidade cidade1, Cidade cidade2) {
    final int R = 6371; // Radius of the earth

    double latDistance = Math.toRadians(cidade2.getLat() - cidade1.getLat());
    double lonDistance = Math.toRadians(cidade2.getLon() - cidade1.getLon());

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(cidade1.getLat()))
        * Math.cos(Math.toRadians(cidade2.getLat())) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c; // * 1000; convert to meters

    // Desabilitando verificação de altura
    double height = 0 - 0;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
  }

  public List<Cidade> filtrarCSV(String fieldName, String value) {
    List<Cidade> cidadeList = setup.filtarCSV(CIDADES_FILE, fieldName, value);

    return cidadeList;
  }
}