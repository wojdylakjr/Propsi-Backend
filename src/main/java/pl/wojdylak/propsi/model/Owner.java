package pl.wojdylak.propsi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
//
//    @Column(name = "name")
//    private String name;

    @ManyToMany
    @JoinTable(name = "rel_owner_user", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Owner(){
    }

    public Owner(Set<User> users) {
        this.users = users;
    }
}