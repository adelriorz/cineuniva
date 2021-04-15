package entities;

import entities.State;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T20:02:21")
@StaticMetamodel(Municipality.class)
public class Municipality_ { 

    public static volatile SingularAttribute<Municipality, Integer> municipalityId;
    public static volatile SingularAttribute<Municipality, Boolean> municipalityStatus;
    public static volatile SingularAttribute<Municipality, State> stateId;
    public static volatile SingularAttribute<Municipality, Date> municipalityCreatedAt;
    public static volatile SingularAttribute<Municipality, Date> municipalityUpdatedAt;
    public static volatile SingularAttribute<Municipality, String> municipalityName;

}