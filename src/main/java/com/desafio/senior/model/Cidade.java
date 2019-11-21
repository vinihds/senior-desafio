package com.desafio.senior.model;

import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vinicius Silveira
 * 
 */
@Entity
@Table(name = "cidade")
public class Cidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idcidade")
  private int idcidade;
  private int ibge_id;
  private String uf;
  private String name;
  private boolean capital = false;
  private BigInteger lon;
  private BigInteger lat;
  private String no_accents;
  private String alternative_names;
  private String microregion;
  private String mesoregion;
}