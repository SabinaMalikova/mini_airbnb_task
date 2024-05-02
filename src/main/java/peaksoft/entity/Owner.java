package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Owner {
    @Id
    @GeneratedValue(generator = "owner_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "owner_gen", sequenceName = "owner_seq", allocationSize = 1 )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    private List<Agency>agencies;
    @OneToMany(mappedBy = "owner",cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<House>houses;
    @OneToMany(mappedBy = "owner",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
    private List<RentInfo>rentInfos;

    public Owner(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public void addHouses(House house) {
        if (this.houses == null) this.houses = new ArrayList<>();
        this.houses.add(house);
    }

    @Override
    public String toString() {
        return "\nOwner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}
