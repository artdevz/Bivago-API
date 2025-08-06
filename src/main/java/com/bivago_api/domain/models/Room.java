package com.bivago_api.domain.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Room {
    
    private UUID id;
    private int capacity;
    private int category;
    private int number;
    private BigDecimal price;
    private Hotel host;

    private List<Reservation> reservations;

    public Room() {}
    public Room(
        UUID id,
        int capacity,
        int category,
        int number,
        BigDecimal price,
        Hotel host
    ) {
        this.id = id;
        setCapacity(capacity);
        setCategory(category);
        setNumber(number);
        setPrice(price);
        setHost(host);
    }

    public UUID getId() { return id; }
    public int getCapacity() { return capacity; }
    public int getCategory() { return category; }
    public int getNumber() { return number; }
    public BigDecimal getPrice() { return price; }
    public Hotel getHost() { return host; }

    public List<Reservation> getReservations() { return reservations; }

    public void setCapacity(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacidade do Quarto deve ser maior que Zero");
        this.capacity = capacity;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    public void setNumber(int number) {
        if (number < 1) throw new IllegalArgumentException("Número do Quarto deve ser positivo absoluto");
        this.number = number;
    }

    public void setPrice(BigDecimal price) {
        if (price.intValue() < 0) throw new IllegalArgumentException("Preço não deve ser negativo");
        this.price = price;
    }

    public void setHost(Hotel host) {
        this.host = host;
    }

}
