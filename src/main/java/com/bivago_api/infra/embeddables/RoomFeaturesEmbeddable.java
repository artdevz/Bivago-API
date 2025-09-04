package com.bivago_api.infra.embeddables;

import java.util.Objects;

import com.bivago_api.domain.models.values.RoomFeatures;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RoomFeaturesEmbeddable {
    
    private boolean airConditioning;
    private boolean bathtub;
    private boolean balcony;
    private boolean tv;

    public RoomFeatures toDomain() { return new RoomFeatures(this.airConditioning, this.bathtub, this.balcony, this.tv); }
    public static RoomFeaturesEmbeddable fromDomain(RoomFeatures roomFeatures) { return new RoomFeaturesEmbeddable(roomFeatures.isAirConditioning(), roomFeatures.isBathtub(), roomFeatures.isBalcony(), roomFeatures.isTV()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomFeaturesEmbeddable)) return false;
        RoomFeaturesEmbeddable that = (RoomFeaturesEmbeddable) o;
        return (
            Objects.equals(airConditioning, that.airConditioning) &&
            Objects.equals(bathtub, that.bathtub) &&
            Objects.equals(balcony, that.balcony) &&
            Objects.equals(tv, that.tv)
        );
    }

    @Override
    public int hashCode() { return Objects.hash(airConditioning, bathtub, balcony, tv); }

}
