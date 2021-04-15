package entities;

import entities.Assistance;
import entities.Movie;
import entities.Room;
import entities.Schedule;
import entities.State;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T20:02:21")
@StaticMetamodel(Billboard.class)
public class Billboard_ { 

    public static volatile ListAttribute<Billboard, Assistance> assistanceList;
    public static volatile SingularAttribute<Billboard, Date> billboardCreatedAt;
    public static volatile SingularAttribute<Billboard, State> stateId;
    public static volatile SingularAttribute<Billboard, Integer> billboardId;
    public static volatile SingularAttribute<Billboard, Boolean> billboardStatus;
    public static volatile SingularAttribute<Billboard, Movie> movieId;
    public static volatile SingularAttribute<Billboard, Date> billboardUpdatedAt;
    public static volatile SingularAttribute<Billboard, Room> roomId;
    public static volatile SingularAttribute<Billboard, Schedule> scheduleId;

}