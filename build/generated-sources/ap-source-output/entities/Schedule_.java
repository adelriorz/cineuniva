package entities;

import entities.Assistance;
import entities.SchedulePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-23T19:17:53")
@StaticMetamodel(Schedule.class)
public class Schedule_ { 

    public static volatile SingularAttribute<Schedule, String> scheduleStart;
    public static volatile SingularAttribute<Schedule, String> scheduleUpdatedAt;
    public static volatile ListAttribute<Schedule, Assistance> assistanceList;
    public static volatile SingularAttribute<Schedule, String> scheduleEnd;
    public static volatile SingularAttribute<Schedule, Short> scheduleStatus;
    public static volatile SingularAttribute<Schedule, SchedulePK> schedulePK;
    public static volatile SingularAttribute<Schedule, String> scheduleCreatedAt;

}