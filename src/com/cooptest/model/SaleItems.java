/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class SaleItems {
    private int id;
    private Sale sale;
    private Product product;
    private Double precoVenda;
    private int quantidade;

    public SaleItems() {
    }

    public SaleItems(int quantidade) {
        this.quantidade = quantidade;
    }

    public SaleItems(int id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }
    
    public SaleItems(int id, Sale sale, Product product, Double precoVenda, int quantidade) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "SaleItems{" + "id=" + id + ", sale=" + sale + ", product=" + product + ", precoVenda=" + precoVenda + ", quantidade=" + quantidade + '}';
    }
}
