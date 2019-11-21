package com.desafio.senior.controller;

import java.util.List;
import java.util.Optional;

import com.desafio.senior.model.Cidade;
import com.desafio.senior.repository.CidadeRepository;
import com.desafio.senior.service.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PutMapping(value = "/cidades/{ibgeid}")
  public ResponseEntity<Cidade> put(@PathVariable(value = "ibgeid") int ibgeid, @RequestBody Cidade cidadeNew) {
    Optional<Cidade> cidadeOld = cidadeRepository.findByIbgeId(ibgeid);

    if (cidadeOld.isPresent()) {
      Cidade cidade = cidadeOld.get();
      int idOld = cidade.getIdCidade();

      // Atualizando objeto
      cidade = cidadeNew;
      // Mantendo o mesmo id do objeto original
      cidade.setIdCidade(idOld);
      cidadeRepository.save(cidade);

      return new ResponseEntity<Cidade>(cidade, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/cidades/{ibgeid}")
  public ResponseEntity<Object> Delete(@PathVariable(value = "iibgeid") int ibgeid) {
    Optional<Cidade> cidade = cidadeRepository.findByIbgeId(ibgeid);

    if (cidade.isPresent()) {
      cidadeRepository.delete(cidade.get());

      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}