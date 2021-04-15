package entities;

import entities.Billboard;
import entities.Municipality;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T20:02:21")
@StaticMetamodel(State.class)
public class State_ { 

    public static volatile SingularAttribute<State, Boolean> stateStatus;
    public static volatile SingularAttribute<State, Date> stateUpdatedAt;
    public static volatile ListAttribute<State, Municipality> municipalityList;
    public static volatile SingularAttribute<State, String> stateName;
    public static volatile ListAttribute<State, Billboard> billboardList;
    public static volatile SingularAttribute<State, Integer> stateId;
    public static volatile SingularAttribute<State, Date> stateCreatedAt;

}