/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Product {
    private int id;
    private ProductGroup productGroup;
    private MeasureUnit measureUnit;
    private Formula formula;
    private String codIdentificacao;
    private String nomeComercial;
    private String descricao;
    private Double precoBase;

    public Product() {
    }

    public Product(int id, ProductGroup productGroup, MeasureUnit measureUnit, Formula formula, String codIdentificacao, String nomeComercial, String descricao, Double precoBase) {
        this.id = id;
        this.productGroup = productGroup;
        this.measureUnit = measureUnit;
        this.formula = formula;
        this.codIdentificacao = codIdentificacao;
        this.nomeComercial = nomeComercial;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public String getCodIdentificacao() {
        return codIdentificacao;
    }

    public void setCodIdentificacao(String codIdentificacao) {
        this.codIdentificacao = codIdentificacao;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productGroup=" + productGroup + ", measureUnit=" + measureUnit + ", formula=" + formula + ", codIdentificacao=" + codIdentificacao + ", nomeComercial=" + nomeComercial + ", descricao=" + descricao + ", precoBase=" + precoBase + '}';
    }
}
