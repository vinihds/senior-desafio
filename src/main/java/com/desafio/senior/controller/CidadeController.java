package com.desafio.senior.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.desafio.senior.model.Cidade;
import com.desafio.senior.repository.CidadeRepository;
import com.desafio.senior.service.CidadeService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinicius Silveira
 * 
 */
@RestController
public class CidadeController {

  @Autowired
  CidadeService cidadeService;
  @Autowired
  CidadeRepository cidadeRepository;

  @GetMapping(value = "/cidades")
  public List<Cidade> getCidades() {
    return cidadeRepository.findAll();
  }

  @GetMapping(value = "/cidades/{ibgeid}")
  public ResponseEntity<Cidade> getCidadesByIbgeId(@PathVariable(name = "ibgeid") int ibgeId) {
    Optional<Cidade> cidade = cidadeRepository.findByIbgeId(ibgeId);

    if (cidade.isPresent()) {
      return new ResponseEntity<Cidade>(cidade.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/cidades")
  public Cidade post(@RequestBody Cidade cidade) {
    return cidadeRepository.save(cidade);
  }

  // @PutMapping(value = "/cidades/{ibgeid}")
  // public ResponseEntity<Cidade> put(@PathVariable(value = "ibgeid") int ibgeId,
  // @RequestBody Cidade cidadeNew) {
  // Optional<Cidade> cidadeOld = cidadeRepository.findByIbgeId(ibgeId);

  // if (cidadeOld.isPresent()) {
  // Cidade cidade = cidadeOld.get();
  // int idOld = cidade.getIdcidade();

  // // Atualizando objeto
  // cidade = cidadeNew;
  // // Mantendo o mesmo id do objeto original
  // cidade.setIdcidade(idOld);
  // cidadeRepository.save(cidade);

  // return new ResponseEntity<Cidade>(cidade, HttpStatus.OK);
  // } else {
  // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // }
  // }

  @DeleteMapping(value = "/cidades/{ibgeid}")
  public ResponseEntity<Object> Delete(@PathVariable(value = "ibgeid") int ibgeId) {
    Optional<Cidade> cidade = cidadeRepository.findByIbgeId(ibgeId);

    if (cidade.isPresent()) {
      cidadeRepository.delete(cidade.get());

      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/pesquisa/cidades/capital")
  public List<Cidade> getCapitais() {
    return cidadeRepository.findByCapitalTrueOrderByName();
  }

  @PostMapping(value = "/cidades/importacao")
  public ResponseEntity<Object> importarDados() {
    try {
      cidadeService.setupCidades();

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/pesquisa/count/cidades/uf/maiormenor")
  public ResponseEntity<String> retornaCountMaiorMenorCidadesPorUf() {
    List<Object[]> countCidadesPorUf = cidadeRepository.retornaCountCidadesPorUf();

    if (!countCidadesPorUf.isEmpty()) {
      JsonArray array = new JsonArray();
      JsonObject cidadeMaior = new JsonObject();
      JsonObject cidadeMenor = new JsonObject();

      cidadeMaior.addProperty("uf", countCidadesPorUf.get(0)[0].toString());
      cidadeMaior.addProperty("count", countCidadesPorUf.get(0)[1].toString());

      cidadeMenor.addProperty("uf", countCidadesPorUf.get(countCidadesPorUf.size() - 1)[0].toString());
      cidadeMenor.addProperty("count", countCidadesPorUf.get(countCidadesPorUf.size() - 1)[1].toString());

      array.add(cidadeMaior);
      array.add(cidadeMenor);

      return new ResponseEntity<String>(array.toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/pesquisa/count/cidades/uf")
  public ResponseEntity<String> countCidadesPorUf() {
    List<Object[]> countCidadesPorUf = cidadeRepository.retornaCountCidadesPorUf();

    if (!countCidadesPorUf.isEmpty()) {
      JsonArray array = new JsonArray();
      JsonObject object = new JsonObject();

      for (Object[] dados : countCidadesPorUf) {
        object = new JsonObject();
        object.addProperty("uf", dados[0].toString());
        object.addProperty("count", dados[1].toString());

        array.add(object);
      }

      return new ResponseEntity<String>(array.toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/cidades/name", params = { "uf" })
  public ResponseEntity<?> cidadesNamePorUf(@RequestParam(value = "uf") String uf) {
    List<Cidade> cidadesPorUf = cidadeRepository.findAllByUf(uf.toUpperCase());

    if (!cidadesPorUf.isEmpty()) {
      List<String> cidadesName = new ArrayList<String>();

      cidadesPorUf.stream().forEach(cidade -> cidadesName.add(cidade.getName()));

      return new ResponseEntity<>(cidadesName, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/pesquisa/count/cidades", params = { "coluna" })
  public ResponseEntity<Long> countCidadesPorColuna(@RequestParam(value = "coluna") String coluna) {
    try {
      long countCidadesPorColuna = cidadeService.countRegistrosPorColuna(coluna);

      return new ResponseEntity<Long>(countCidadesPorColuna, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/pesquisa/count/cidades")
  public ResponseEntity<Integer> countCidades() {
    try {
      int count = cidadeRepository.countCidades();

      return new ResponseEntity<Integer>(count, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}