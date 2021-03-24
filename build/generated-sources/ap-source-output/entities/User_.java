package entities;

import entities.Assistance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-23T19:17:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SingularAttribute<User, String> userUpdatedAt;
    public static volatile SingularAttribute<User, Short> userStatus;
    public static volatile ListAttribute<User, Assistance> assistanceList;
    public static volatile SingularAttribute<User, String> userCreatedAt;
    public static volatile SingularAttribute<User, Short> userType;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Integer> userId;

}