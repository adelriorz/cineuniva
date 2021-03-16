package entities;

import entities.MovieLocation;
import entities.MunicipalityPK;
import entities.State;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T00:05:55")
@StaticMetamodel(Municipality.class)
public class Municipality_ { 

    public static volatile SingularAttribute<Municipality, MunicipalityPK> municipalityPK;
    public static volatile SingularAttribute<Municipality, Short> municipalityStatus;
    public static volatile SingularAttribute<Municipality, Date> municipalityCreatedAt;
    public static volatile SingularAttribute<Municipality, State> state;
    public static volatile SingularAttribute<Municipality, Date> municipalityUpdatedAt;
    public static volatile SingularAttribute<Municipality, String> municipalityName;
    public static volatile ListAttribute<Municipality, MovieLocation> movieLocationList;

}