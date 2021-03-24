package entities;

import entities.Assistance;
import entities.MunicipalityPK;
import entities.State;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-23T19:17:53")
@StaticMetamodel(Municipality.class)
public class Municipality_ { 

    public static volatile SingularAttribute<Municipality, MunicipalityPK> municipalityPK;
    public static volatile ListAttribute<Municipality, Assistance> assistanceList;
    public static volatile SingularAttribute<Municipality, Short> municipalityStatus;
    public static volatile SingularAttribute<Municipality, String> municipalityCreatedAt;
    public static volatile SingularAttribute<Municipality, State> state;
    public static volatile SingularAttribute<Municipality, String> municipalityUpdatedAt;
    public static volatile SingularAttribute<Municipality, String> municipalityName;

}