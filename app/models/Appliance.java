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
public class Appliance extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Constraints.Required
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "appliance")
    List<ApplianceUser> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "appliance")
    List<Type> types = new ArrayList<>();

    public static Finder<Long, Appliance> find = new Finder<Long, Appliance>(Long.class, Appliance.class);

    public Appliance() {
    }

    public int addType(Type newType) {
        if (newType.id == null)
            return -1;

        this.types.add(newType);
        this.save();

        return 1;
    }
}
