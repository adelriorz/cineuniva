package entities;

import entities.Billboard;
import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T22:31:54")
@StaticMetamodel(Assistance.class)
public class Assistance_ { 

    public static volatile SingularAttribute<Assistance, Boolean> assistanceStatus;
    public static volatile SingularAttribute<Assistance, Date> assistanceUpdatedAt;
    public static volatile SingularAttribute<Assistance, Billboard> billboardId;
    public static volatile SingularAttribute<Assistance, Integer> assistanceId;
    public static volatile SingularAttribute<Assistance, User> userId;
    public static volatile SingularAttribute<Assistance, Date> assistanceCreatedAt;

}