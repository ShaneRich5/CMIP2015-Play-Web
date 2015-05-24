package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by Shane on 5/23/2015.
 */
@Entity
public class Address extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String street;
    public String town;
    public String parish;

    public String landmark;

    @JsonIgnore
    @OneToOne
    public User user;

    public Address(String street, String town, String parish) {
        this.street = street;
        this.town = town;
        this.parish = parish;
    }

    public static Finder<Long, Address> find = new Finder<Long, Address>(Long.class, Address.class);
}
