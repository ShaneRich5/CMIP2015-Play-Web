package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.relations.ApplianceUser;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/23/2015.
 */
@Entity
@Table(name = "users")
public class User extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String firstName;
    public String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<ApplianceUser> appliances = new ArrayList<>();

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public User() {
    }
}
