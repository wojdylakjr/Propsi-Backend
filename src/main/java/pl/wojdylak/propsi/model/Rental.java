package pl.wojdylak.propsi.model;


import javax.persistence.*;


@Entity
@Table(name = "rental")
public class Rental {
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
