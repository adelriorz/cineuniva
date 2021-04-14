package entities;

import entities.Billboard;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-14T01:27:30")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, String> movieDirector;
    public static volatile SingularAttribute<Movie, String> movieProducer;
    public static volatile SingularAttribute<Movie, Boolean> movieStatus;
    public static volatile SingularAttribute<Movie, Date> movieUpdatedAt;
    public static volatile ListAttribute<Movie, Billboard> billboardList;
    public static volatile SingularAttribute<Movie, String> movieClassification;
    public static volatile SingularAttribute<Movie, String> movieGenre;
    public static volatile SingularAttribute<Movie, Integer> movieId;
    public static volatile SingularAttribute<Movie, Integer> movieDuration;
    public static volatile SingularAttribute<Movie, String> movieName;
    public static volatile SingularAttribute<Movie, Date> movieCreatedAt;

}