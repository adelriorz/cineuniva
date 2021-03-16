package entities;

import entities.Schedule;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T00:23:58")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, Short> roomStatus;
    public static volatile SingularAttribute<Room, Date> roomUpdatedAt;
    public static volatile SingularAttribute<Room, Integer> roomNumber;
    public static volatile ListAttribute<Room, Schedule> scheduleList;
    public static volatile SingularAttribute<Room, Date> roomCreatedAt;
    public static volatile SingularAttribute<Room, Integer> roomId;

}