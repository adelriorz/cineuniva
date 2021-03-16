package entities;

import entities.MovieSchedule;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T00:23:58")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, String> movieDirector;
    public static volatile SingularAttribute<Movie, String> movieProducer;
    public static volatile SingularAttribute<Movie, Short> movieStatus;
    public static volatile SingularAttribute<Movie, Date> movieUpdatedAt;
    public static volatile SingularAttribute<Movie, String> movieClassification;
    public static volatile SingularAttribute<Movie, Integer> movieId;
    public static volatile SingularAttribute<Movie, Integer> movieDuration;
    public static volatile SingularAttribute<Movie, String> movieName;
    public static volatile ListAttribute<Movie, MovieSchedule> movieScheduleList;
    public static volatile SingularAttribute<Movie, Date> movieCreatedAt;

}