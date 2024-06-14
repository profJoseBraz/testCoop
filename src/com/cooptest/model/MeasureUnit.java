/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class MeasureUnit {
    private int id;
    private String nome;
    private String simbolo;
    private String descricao;

    public MeasureUnit() {
    }

    public MeasureUnit(String simbolo) {
        this.simbolo = simbolo;
    }

    public MeasureUnit(int id, String nome, String simbolo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.simbolo = simbolo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "MeasureUnit{" + "id=" + id + ", nome=" + nome + ", simbolo=" + simbolo + ", descricao=" + descricao + '}';
    }
}
