package com.bivago_api.domain.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class User {
    
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 64;
    private static final int CPF_LENGTH = 11;
    private static final int MINIMAL_AGE = 18;

    private UUID id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private LocalDate birthday;
    private int role;

    private List<Hotel> hotels;
    private List<Reservation> reservations;

    public User() {}
    public User(
        UUID id,
        String name,
        String email,
        String password,
        String cpf,
        LocalDate birthday,
        int role
    ) {
        this.id = id;
        setName(name);
        setEmail(email);
        setPassword(password);
        setCPF(cpf);
        setBirthday(birthday);
        setRole(role);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCPF() { return cpf; }
    public LocalDate getBirthday() { return birthday; }
    public int getRole() { return role; }

    public List<Hotel> getHotels() { return hotels; }
    public List<Reservation> getReservations() { return reservations; }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Nome deve ter entre " + MIN_NAME_LENGTH + " e " + MAX_NAME_LENGTH + " caracteres");
        this.name = name;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password; // Encoder vem antes, então não dá pra verificar nada aqui
    }

    public void setCPF(String cpf) {
        validateCPF(cpf);
        this.cpf = cpf;
    }

    public void setBirthday(LocalDate birthday) {   
        LocalDate today = LocalDate.now();
        LocalDate minAllowed = today.minusYears(MINIMAL_AGE);  

        if (birthday.isAfter(minAllowed)) throw new IllegalArgumentException("Usuário precisa ter pelo menos " + MINIMAL_AGE + " anos");
        this.birthday = birthday;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private void validateEmail(String email) {
        if (!(email.matches(EMAIL_REGEX))) throw new IllegalArgumentException("Formato de Email inválido");
    }

    private void validateCPF(String cpf) {
        if (cpf.length() != CPF_LENGTH) throw new IllegalArgumentException("Formato de CPF inválido");

        // TO-DO Verificar o cálculo
    }

}
