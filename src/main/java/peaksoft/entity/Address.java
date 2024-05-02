package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Address {
    @Id
    @GeneratedValue(generator = "address_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    private Long id;
    private String city;
    private String region;
    @Column(unique = true)
    private String street;
    @OneToOne(mappedBy = "address")
    private Agency agency;
    @OneToOne
    private House house;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }

    @Override
    public String toString() {
        return "\n Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
