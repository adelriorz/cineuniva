package entities;

import entities.Billboard;
import entities.Schedule;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T01:27:30")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, Boolean> roomStatus;
    public static volatile SingularAttribute<Room, Date> roomUpdatedAt;
    public static volatile SingularAttribute<Room, Integer> roomNumber;
    public static volatile ListAttribute<Room, Schedule> scheduleList;
    public static volatile ListAttribute<Room, Billboard> billboardList;
    public static volatile SingularAttribute<Room, Date> roomCreatedAt;
    public static volatile SingularAttribute<Room, Integer> roomId;

}