package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rent_Info {
    @Id
    @GeneratedValue(generator = "rent_info_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rent_info_gen", sequenceName = "rent_info_seq", allocationSize = 1)
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;

    @OneToOne
    private House house;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Agency agency;
    @ManyToOne
    private Owner owner;





}
