/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Formula {
    private int id;
    private String formula;
    private String princAtivo;

    public Formula() {
    }

    public Formula(int id, String formula, String princAtivo) {
        this.id = id;
        this.formula = formula;
        this.princAtivo = princAtivo;
    }

    @Override
    public String toString() {
        return "Formula{" + "id=" + id + ", formula=" + formula + ", princAtivo=" + princAtivo + '}';
    }
}
