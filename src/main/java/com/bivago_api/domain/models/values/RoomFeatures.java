package com.bivago_api.domain.models.values;

public class RoomFeatures {
    private boolean airConditioning;
    private boolean bathtub;
    private boolean balcony;
    private boolean tv;

    public RoomFeatures() {}
    public RoomFeatures(boolean airConditioning, boolean bathtub, boolean balcony, boolean tv) {
        setAirConditioning(airConditioning);
        setBathtub(bathtub);
        setBalcony(balcony);
        setTV(tv);
    }

    public boolean isAirConditioning() { return airConditioning; }
    public boolean isBathtub() { return bathtub; }
    public boolean isBalcony() { return balcony; }
    public boolean isTV() { return tv; }

    public void setAirConditioning(boolean airConditioning) { this.airConditioning = airConditioning; }
    public void setBathtub(boolean bathtub) { this.bathtub = bathtub; }
    public void setBalcony(boolean balcony) { this.balcony = balcony; }
    public void setTV(boolean tv) { this.tv = tv; }
}
