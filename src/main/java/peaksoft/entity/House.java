package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.HouseType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House {
    @Id
    @GeneratedValue(generator = "house_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    private String description;
    private int room;
    private boolean furniture;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Owner owner;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
    private Address address;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
    private RentInfo rentInfo;


    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture, RentInfo rentInfo) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
        this.rentInfo = rentInfo;
    }

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture, Address address, RentInfo rentInfo) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
        this.address = address;
        this.rentInfo = rentInfo;


    }

    @Override
    public String toString() {
        return "\nHouse{" +
                "id=" + id +
                ", houseType=" + houseType +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", room=" + room +
                ", furniture=" + furniture +
                '}';
    }
}
