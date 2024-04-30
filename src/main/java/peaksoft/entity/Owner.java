package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @OneToMany(mappedBy = "owner")
    private List<House>houses;
    @OneToMany(mappedBy = "owner")
    private List<Rent_Info>rentInfos;















}
