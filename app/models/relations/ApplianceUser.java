package models.relations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Appliance;
import models.Type;
import models.User;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Shane on 5/23/2015.
 */
@Entity
public class ApplianceUser {
    @Id
    @GeneratedValue
    public Long id;

    @JsonIgnore
    @ManyToOne
    public User user;

    @JsonIgnore
    @ManyToOne
    public Appliance appliance;

    @JsonIgnore
    @ManyToOne
    public Type type;

    public ApplianceUser(User user, Appliance appliance) {
        this.user = user;
        this.appliance = appliance;
    }

    public ApplianceUser(User user, Appliance appliance, Type type) {
        this.user = user;
        this.appliance = appliance;
        this.type = type;
    }

    public static Model.Finder<Long, ApplianceUser> find = new Model.Finder<Long, ApplianceUser>(Long.class, ApplianceUser.class);
}
