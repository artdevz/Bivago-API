package com.bivago_api.infra.embeddables;

import java.util.Objects;

import com.bivago_api.domain.enums.Country;
import com.bivago_api.domain.models.values.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressEmbeddable {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Column(nullable = false)
    private String division;

    @Column(nullable = false)
    private String city;

    private String neighbor;
    private String street;
    private int number;

    public Address toDomain() { return new Address(this.country, this.division, this.city, this.neighbor, this.street, this.number); }
    public static AddressEmbeddable fromDomain(Address address) { return new AddressEmbeddable(address.getCountry(), address.getDivsion(), address.getCity(), address.getNeighbor(), address.getStreet(), address.getNumber()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEmbeddable)) return false;
        AddressEmbeddable that = (AddressEmbeddable) o;
        return (
            Objects.equals(country, that.country) &&
            Objects.equals(division, that.division) &&
            Objects.equals(city, that.city) &&
            Objects.equals(neighbor, that.neighbor) &&
            Objects.equals(street, that.street) &&
            Objects.equals(number, that.number)
        );
    }

    @Override
    public int hashCode() { return Objects.hash(country, division, city, neighbor, street, number); }

}
