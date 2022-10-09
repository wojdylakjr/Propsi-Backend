package pl.wojdylak.propsi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "premises")
public class Premises {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rent_price")
    private Double rentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Property property;

    @OneToMany(mappedBy = "premises")
    private Set<Rental> rentals = new HashSet<>();
}
