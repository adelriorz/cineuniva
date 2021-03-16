package entities;

import entities.Municipality;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-15T18:08:51")
@StaticMetamodel(State.class)
public class State_ { 

    public static volatile SingularAttribute<State, Short> stateStatus;
    public static volatile SingularAttribute<State, Date> stateUpdatedAt;
    public static volatile ListAttribute<State, Municipality> municipalityList;
    public static volatile SingularAttribute<State, String> stateName;
    public static volatile SingularAttribute<State, Integer> stateId;
    public static volatile SingularAttribute<State, Date> stateCreatedAt;

}