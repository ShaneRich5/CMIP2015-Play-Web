package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.relations.ApplianceUser;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/23/2015.
 */
@Entity
public class Type extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Constraints.Required
    public String name;
    public Double watts;

    @JsonIgnore
    @ManyToOne
    public Appliance appliance;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    public List<ApplianceUser> applianceUsers = new ArrayList<>();

    public static Finder<Long, Type> find = new Finder<Long, Type>(Long.class, Type.class);

    public Type() {
    }
}
