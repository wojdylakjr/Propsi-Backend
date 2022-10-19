package pl.wojdylak.propsi.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "rental")
public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "info")
    private String info;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private Premises premises;

}
