package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(generator = "customer_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    @OneToMany(mappedBy = "customer")
    private List<RentInfo> rentInfo;




}
