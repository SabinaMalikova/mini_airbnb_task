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
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.REMOVE})
    private List<RentInfo> rentInfo;

    public Customer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }

    @Override
    public String toString() {
        return "\nCustomer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", familyStatus=" + familyStatus +
                '}';
    }
}
