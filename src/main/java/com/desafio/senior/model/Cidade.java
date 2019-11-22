package com.desafio.senior.model;

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
  @Column(name = "ibge_id")
  private int ibgeId;
  @Column(name = "uf")
  private String uf;
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @Column(name = "capital")
  private boolean capital = false;
  @Column(name = "lon")
  private Double lon;
  @Column(name = "lat")
  private Double lat;
  @Column(name = "no_accents")
  private String noAccents;
  @Column(name = "alternative_names")
  private String alternativeNames;
  @Column(name = "microregion")
  private String microregion;
  @Column(name = "mesoregion")
  private String mesoregion;

  public Cidade() {
  }

  public int getIdcidade() {
    return idcidade;
  }

  public void setIdcidade(int idcidade) {
    this.idcidade = idcidade;
  }

  public int getIbgeId() {
    return ibgeId;
  }

  public void setIbgeId(int ibgeId) {
    this.ibgeId = ibgeId;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isCapital() {
    return capital;
  }

  public void setCapital(boolean capital) {
    this.capital = capital;
  }

  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public String getNoAccents() {
    return noAccents;
  }

  public void setNoAccents(String noAccents) {
    this.noAccents = noAccents;
  }

  public String getAlternativeNames() {
    return alternativeNames;
  }

  public void setAlternativeNames(String alternativeNames) {
    this.alternativeNames = alternativeNames;
  }

  public String getMicroregion() {
    return microregion;
  }

  public void setMicroregion(String microregion) {
    this.microregion = microregion;
  }

  public String getMesoregion() {
    return mesoregion;
  }

  public void setMesoregion(String mesoregion) {
    this.mesoregion = mesoregion;
  }

}