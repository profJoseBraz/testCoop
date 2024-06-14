/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.model;

/**
 *
 * @author jose_
 */
public class Cooperated {
    private int id;
    private Nationality nationality;
    private Concept concept;
    private Address address;
    private PersonType personType;
    private String nome;
    private String email;
    private String telefone;

    public Cooperated() {
    }

    public Cooperated(int id, Nationality nationality, Concept concept, Address address, PersonType personType, String nome, String email, String telefone) {
        this.id = id;
        this.nationality = nationality;
        this.concept = concept;
        this.address = address;
        this.personType = personType;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cooperated{" + "id=" + id + ", nationality=" + nationality + ", concept=" + concept + ", address=" + address + ", personType=" + personType + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + '}';
    }
}
