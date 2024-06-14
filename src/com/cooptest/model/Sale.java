/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Sale {
    private int id;
    private Unit unit;
    private Cooperated cooperated;
    private Double totalPrice;

    public Sale() {
    }

    public Sale(int id, Unit unit, Cooperated cooperated, Double totalPrice) {
        this.id = id;
        this.unit = unit;
        this.cooperated = cooperated;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Cooperated getCooperated() {
        return cooperated;
    }

    public void setCooperated(Cooperated cooperated) {
        this.cooperated = cooperated;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", unit=" + unit + ", cooperated=" + cooperated + ", totalPrice=" + totalPrice + '}';
    }
}
