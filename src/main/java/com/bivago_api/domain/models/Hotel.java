package com.bivago_api.domain.models;

import java.util.List;
import java.util.UUID;

import com.bivago_api.domain.models.values.Address;

public class Hotel {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 5;
    
    private UUID id;
    private User owner;
    private String name;
    private Address address;
    private float score;

    private List<Room> rooms;

    public Hotel() {}
    public Hotel(UUID id, User owner, String name, Address address, float score) {
        this.id = id;
        setOwner(owner);
        setAddress(address);
        setScore(score);
    }

    public UUID getId() { return id; }
    public User getOwner() { return owner; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public float getScore() { return score; }

    public List<Room> getRooms() { return rooms; }

    public void setOwner(User owner) {
        if (owner.getRole() != 1) throw new IllegalArgumentException("Dono do Hotel deve ter papel de HotelOwner");
        this.owner = owner;
    }

    public void setName(String name) {
        if (name.length() < 3 || name.length() > 32) throw new IllegalArgumentException("Nome do Hotel deve ter entre " + 8 + " e " + 32 + " caracteres");
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setScore(float score) {
        if (score < MIN_SCORE || score > MAX_SCORE) throw new IllegalArgumentException("Pontuação deve está entre " + MIN_SCORE + " e " + MAX_SCORE + " pontos");
        this.score = score;
    }

}
