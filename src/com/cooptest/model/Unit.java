/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Unit {
    private int id;
    private County county;
    private String codIdentificacao;
    private String nome;

    public Unit() {
    }

    public Unit(int id, County county, String codIdentificacao, String nome) {
        this.id = id;
        this.county = county;
        this.codIdentificacao = codIdentificacao;
        this.nome = nome;
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

    public String getCodIdentificacao() {
        return codIdentificacao;
    }

    public void setCodIdentificacao(String codIdentificacao) {
        this.codIdentificacao = codIdentificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Unit{" + "id=" + id + ", county=" + county + ", codIdentificacao=" + codIdentificacao + ", nome=" + nome + '}';
    }
}
