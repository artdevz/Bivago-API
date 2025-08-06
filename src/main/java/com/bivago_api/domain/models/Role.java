package com.bivago_api.domain.models;

import java.util.UUID;

public class Role {

    private static final int MAX_NAME_LENGTH = 16;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_DESCRIPTION_LENGTH = 128;

    private UUID id;
    private String name;
    private String description;

    public Role() {};
    public Role(
        UUID id, 
        String name, 
        String description
    ) {
        this.id = id;
        setName(name);
        setDescription(description);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Nome do cargo deve ter entre " + MIN_NAME_LENGTH + " e " + MAX_NAME_LENGTH + " caractéres");
        this.name = name;
    }

    public void setDescription(String description) {
        if (description.length() < MIN_NAME_LENGTH || description.length() > MAX_DESCRIPTION_LENGTH) throw new IllegalArgumentException("Descrição do cargo deve ter entre " + MIN_NAME_LENGTH + " e " + MAX_DESCRIPTION_LENGTH + " caractéres");
        this.description = description;
    }
    
}
