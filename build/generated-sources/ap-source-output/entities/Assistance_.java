package entities;

import entities.AssistancePK;
import entities.Movie;
import entities.Municipality;
import entities.Room;
import entities.Schedule;
import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-25T23:18:07")
@StaticMetamodel(Assistance.class)
public class Assistance_ { 

    public static volatile SingularAttribute<Assistance, Boolean> assistanceStatus;
    public static volatile SingularAttribute<Assistance, Schedule> schedule;
    public static volatile SingularAttribute<Assistance, Movie> movie;
    public static volatile SingularAttribute<Assistance, Date> assistanceUpdatedAt;
    public static volatile SingularAttribute<Assistance, Municipality> municipality;
    public static volatile SingularAttribute<Assistance, AssistancePK> assistancePK;
    public static volatile SingularAttribute<Assistance, User> user;
    public static volatile SingularAttribute<Assistance, Room> room;
    public static volatile SingularAttribute<Assistance, Date> assistanceCreatedAt;

}