package com.bivago_api.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bivago_api.domain.enums.RoomType;
import com.bivago_api.domain.models.values.RoomFeatures;

public class Room {
    
    private UUID id;
    private byte capacity;
    private RoomType category;
    private int number;
    private BigDecimal price;
    private RoomFeatures roomFeatures;
    private Hotel host;

    private List<Reservation> reservations = new ArrayList<>();

    public Room() {}
    public Room(
        UUID id,
        byte capacity,
        RoomType category,
        int number,
        BigDecimal price,
        RoomFeatures roomFeatures,
        Hotel host
    ) {
        this.id = id;
        setCapacity(capacity);
        setCategory(category);
        setNumber(number);
        setPrice(price);
        setRoomFeatures(roomFeatures);
        setHost(host);
    }

    public UUID getId() { return id; }
    public byte getCapacity() { return capacity; }
    public RoomType getCategory() { return category; }
    public int getNumber() { return number; }
    public BigDecimal getPrice() { return price; }
    public RoomFeatures getRoomFeatures() { return roomFeatures; }
    public Hotel getHost() { return host; }

    public List<Reservation> getReservations() { return reservations; }

    public void setCapacity(byte capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacidade do Quarto deve ser maior que Zero");
        this.capacity = capacity;
    }

    public void setCategory(RoomType category) {
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

    public void setRoomFeatures(RoomFeatures roomFeatures) { this.roomFeatures = roomFeatures; }

    public void setHost(Hotel host) {
        this.host = host;
    }

}
