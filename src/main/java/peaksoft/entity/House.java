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
@ToString
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








}
