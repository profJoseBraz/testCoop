/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Address {
    private int id;
    private County county;
    private String numero;
    private String cep;
    private String bairro;
    private String rua;

    public Address() {
    }

    public Address(int id, County county, String numero, String cep, String bairro, String rua) {
        this.id = id;
        this.county = county;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", county=" + county + ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro + ", rua=" + rua + '}';
    }
}
