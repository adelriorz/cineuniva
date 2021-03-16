package entities;

import entities.MovieLocationPK;
import entities.MovieSchedule;
import entities.Municipality;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T00:23:58")
@StaticMetamodel(MovieLocation.class)
public class MovieLocation_ { 

    public static volatile ListAttribute<MovieLocation, User> userList;
    public static volatile SingularAttribute<MovieLocation, Municipality> municipality;
    public static volatile SingularAttribute<MovieLocation, MovieLocationPK> movieLocationPK;
    public static volatile SingularAttribute<MovieLocation, MovieSchedule> movieSchedule;

}