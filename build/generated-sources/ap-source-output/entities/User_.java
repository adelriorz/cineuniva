package entities;

import entities.Assistance;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T22:31:54")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SingularAttribute<User, Date> userUpdatedAt;
    public static volatile SingularAttribute<User, Boolean> userStatus;
    public static volatile ListAttribute<User, Assistance> assistanceList;
    public static volatile SingularAttribute<User, Date> userCreatedAt;
    public static volatile SingularAttribute<User, Boolean> userType;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Integer> userId;

}