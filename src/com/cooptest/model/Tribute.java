/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Tribute {
    private int id;
    private State stateFrom;
    private State stateTo;
    private ProductGroup productGroup;
    private PersonType personType;
    private Double icms;

    public Tribute() {
    }

    public Tribute(Double icms) {
        this.icms = icms;
    }
    
    public Tribute(int id, State stateFrom, State stateTo, ProductGroup productGroup, PersonType personType, Double icms) {
        this.id = id;
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;
        this.productGroup = productGroup;
        this.personType = personType;
        this.icms = icms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getStateFrom() {
        return stateFrom;
    }

    public void setStateFrom(State stateFrom) {
        this.stateFrom = stateFrom;
    }

    public State getStateTo() {
        return stateTo;
    }

    public void setStateTo(State stateTo) {
        this.stateTo = stateTo;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    @Override
    public String toString() {
        return "Tribute{" + "id=" + id + ", stateFrom=" + stateFrom + ", stateTo=" + stateTo + ", productGroup=" + productGroup + ", personType=" + personType + ", icms=" + icms + '}';
    }
}
