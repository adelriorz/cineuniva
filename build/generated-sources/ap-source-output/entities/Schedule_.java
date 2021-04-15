package entities;

import entities.Billboard;
import entities.Room;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T20:02:21")
@StaticMetamodel(Schedule.class)
public class Schedule_ { 

    public static volatile SingularAttribute<Schedule, Date> scheduleStart;
    public static volatile SingularAttribute<Schedule, Date> scheduleUpdatedAt;
    public static volatile ListAttribute<Schedule, Billboard> billboardList;
    public static volatile SingularAttribute<Schedule, Date> scheduleEnd;
    public static volatile SingularAttribute<Schedule, Boolean> scheduleStatus;
    public static volatile SingularAttribute<Schedule, Integer> scheduleId;
    public static volatile SingularAttribute<Schedule, Room> roomId;
    public static volatile SingularAttribute<Schedule, Date> scheduleCreatedAt;

}