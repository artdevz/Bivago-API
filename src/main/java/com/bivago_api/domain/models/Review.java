package com.bivago_api.domain.models;

import java.time.LocalDate;
import java.util.UUID;

public class Review {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 5; // Talvez fosse bom colocar em uma parte compartilhada
    private static final int MAX_COMMENT_LENGTH = 500;

    private UUID id;
    private int rating;
    private String comment;
    private Reservation reservation;
    private User user;

    public Review() {}
    public Review(
        UUID id,
        int rating,
        String comment,
        Reservation reservation,
        User user
    ) {
        this.id = id;
        setRating(rating);
        setComment(comment);
        setUser(user);
        setReservation(reservation);
    }

    public UUID getId() { return id; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Reservation getReservation() { return reservation; }
    public User getUser() { return user; }

    public void setRating(int rating) {
        if (rating < MIN_SCORE || rating > MAX_SCORE) throw new IllegalArgumentException("Pontuação deve ser entre " + MIN_SCORE + " e " + MAX_SCORE);
        this.rating = rating;
    }

    public void setComment(String comment) {
        if (comment.length() > MAX_COMMENT_LENGTH) throw new IllegalArgumentException("Comentário deve ter no máximo " + MAX_COMMENT_LENGTH + " caracteres");
        this.comment = comment;
    }
    
    public void setReservation(Reservation reservation) {
        if (reservation.getCheckOut().isAfter(LocalDate.now())) throw new IllegalArgumentException("Avaliação só deve ser feita depois do Checkout");
        if (!reservation.getGuest().getId().equals(this.user.getId())) throw new IllegalArgumentException("ID do Usuário avaliando deve ser o mesmo ID do usuário da Reserva");
        this.reservation = reservation;
    }

    public void setUser(User user) { this.user = user; }
    
}
