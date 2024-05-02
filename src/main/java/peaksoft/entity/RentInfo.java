package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RentInfo {
    @Id
    @GeneratedValue(generator = "rent_info_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rent_info_gen", sequenceName = "rent_info_seq", allocationSize = 1)
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Agency agency;
    @ManyToOne
    private Owner owner;

    public RentInfo(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "\nRentInfo{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", customer=" + customer +
                ", agency=" + agency +
                ", owner=" + owner +
                '}';
    }
}
