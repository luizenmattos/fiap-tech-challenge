package com.fiap.techchallenge1_G13.domain.model;

public class Address {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String cep;

    public Address() {}

    public Address(Long id, String street, String number, String city, String cep){
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
