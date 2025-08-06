package com.bivago_api.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    
    private UUID id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int nop; // Number of People
    private BigDecimal price;
    private User guest;
    private Room room;

    public Reservation() {}
    public Reservation(
        UUID id,
        LocalDate checkIn,
        LocalDate checkOut,
        int nop,
        BigDecimal price,
        User guest,
        Room room
    ) {
        this.id = id;
        setCheckIn(checkIn);
        setCheckOut(checkOut);
        setNop(nop);
        setPrice(price);
        setGuest(guest);
        setRoom(room);
    }

    public UUID getId() { return id; }
    public User getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public int getNop() { return nop; }
    public BigDecimal getPrice() { return price; }

    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    public void setNop(int nop) { 
        if (nop > room.getCapacity()) throw new IllegalArgumentException("Número de pessoas não deve ser maior que a capacidade do quarto");
        this.nop = nop; 
    }

    public void setPrice(BigDecimal price) { 
        if (price.intValueExact() < 0) throw new IllegalArgumentException("Valor não deve menor que ZERO");
        this.price = price; 
    }

    public void setGuest(User guest) { this.guest = guest; }
    public void setRoom(Room room) { this.room = room; }

}
