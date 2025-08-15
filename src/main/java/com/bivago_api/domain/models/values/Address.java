package com.bivago_api.domain.models.values;

public class Address {
    
    private String country;
    private String division;
    private String city;

    private String neighbor;
    private String street;
    private int number;

    public Address() {}
    public Address(String country, String division, String city, String neighbor, String street, int number) {
        setCountry(country);
        setDivision(division);
        setCity(city);
        setNeighbor(neighbor);
        setStreet(street);
        setNumber(number);
    }

    public String getCountry() { return country; }
    public String getDivsion() { return division; }
    public String getCity() { return city; }
    public String getNeighbor() { return neighbor; }
    public String getStreet() { return street; }
    public int getNumber() { return number; }

    public void setCountry(String country) {
        // Colocar um Enum depois...
        this.country = country;
    }

    public void setDivision(String division) {
        if (division.length() < 3 || division.length() > 32) throw new IllegalArgumentException("Nome da Subdivisão do país deve ter entre " + 3 + " e " + 32 + " caracteres");
        this.division = division;
    }

    public void setCity(String city) {
        if (city.length() < 3 || city.length() > 32) throw new IllegalArgumentException("Nome da cidade do país deve ter entre " + 3 + " e " + 32 + " caracteres");
        this.city = city;
    }

    public void setNeighbor(String neighbor) {
        if (neighbor.length() < 3 || neighbor.length() > 32) throw new IllegalArgumentException("Nome do bairro do país deve ter entre " + 3 + " e " + 32 + " caracteres");
        this.neighbor = neighbor;
    }

    public void setStreet(String street) {
        if (street.length() < 3 || street.length() > 32) throw new IllegalArgumentException("Nome da rua/avenida do país deve ter entre " + 3 + " e " + 32 + " caracteres");
        this.street = street;
    }

    public void setNumber(int number) {
        if (number < 0) throw new IllegalArgumentException("Número da rua devem ser positivos");
        this.number = number;
    }

}
