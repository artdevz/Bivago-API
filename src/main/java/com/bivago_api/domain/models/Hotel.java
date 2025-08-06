package com.bivago_api.domain.models;

import java.util.List;
import java.util.UUID;

import com.bivago_api.domain.models.values.Address;

public class Hotel {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 5;
    
    private UUID id;
    private String name;
    private float score;
    private Address address;
    private User owner;

    private List<Room> rooms;

    public Hotel() {}
    public Hotel(
        UUID id,
        String name,
        float score,
        Address address,
        User owner
    ) {
        this.id = id;
        setName(name);
        setScore(score);
        setAddress(address);
        setOwner(owner);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public float getScore() { return score; }
    public Address getAddress() { return address; }
    public User getOwner() { return owner; }

    public List<Room> getRooms() { return rooms; }

    public void setName(String name) {
        if (name.length() < 3 || name.length() > 32) throw new IllegalArgumentException("Nome do Hotel deve ter entre " + 8 + " e " + 32 + " caracteres");
        this.name = name;
    }

    public void setScore(float score) {
        if (score < MIN_SCORE || score > MAX_SCORE) throw new IllegalArgumentException("Pontuação deve está entre " + MIN_SCORE + " e " + MAX_SCORE + " pontos");
        this.score = score;
    }

    public void setAddress(Address address) { this.address = address; }

    public void setOwner(User owner) {
        if (owner.getRole() != 1) throw new IllegalArgumentException("Dono do Hotel deve ter papel de HotelOwner");
        this.owner = owner;
    }

}
