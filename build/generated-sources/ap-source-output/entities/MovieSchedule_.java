package entities;

import entities.Movie;
import entities.MovieLocation;
import entities.MovieSchedulePK;
import entities.Schedule;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T00:05:55")
@StaticMetamodel(MovieSchedule.class)
public class MovieSchedule_ { 

    public static volatile SingularAttribute<MovieSchedule, Schedule> schedule;
    public static volatile SingularAttribute<MovieSchedule, MovieSchedulePK> movieSchedulePK;
    public static volatile SingularAttribute<MovieSchedule, Movie> movie;
    public static volatile ListAttribute<MovieSchedule, MovieLocation> movieLocationList;

}