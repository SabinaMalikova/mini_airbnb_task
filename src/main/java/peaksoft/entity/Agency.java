package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Agency {
    @Id
    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String agency_name;
    private Long phone_number;
    @OneToOne
    private Address address;
    @ManyToMany(mappedBy = "agencies")
    private List<Owner>owners;
    @OneToMany
    private List<RentInfo>rentInfos;






}
