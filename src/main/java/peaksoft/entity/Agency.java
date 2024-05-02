package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.service.serviceImpl.AgencyServiceImpl;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Agency {
    @Id
    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String  agency_name;
    @AgencyServiceImpl.PhoneNumberConstraint(regex =  "^\\+996\\d{13}$" ,message = "Invalid phone number")
    @Column(name = "phone_number")
    private Long phone_number;
    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.REMOVE})
    private Address address;
    @ManyToMany(mappedBy = "agencies")
    private List<Owner>owners;
    @OneToMany(cascade = {CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<RentInfo>rentInfos;

    public Agency(String agency_name, Long phone_number) {
        this.agency_name = agency_name;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "\nAgency{" +
                "id=" + id +
                ", agency_name='" + agency_name + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
