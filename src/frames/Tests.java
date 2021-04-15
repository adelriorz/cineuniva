
/*
package frames;
/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code to test db availability and functionality
*/
import entities.Movie;
import entities.User;
import controllers.MovieJpaController;
import controllers.UserJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;

//    public MovieJpaController() {
//        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
//    }

public class Tests {
    public static void main(String[] args) {
        Movie m = new Movie();
//        User u = new User();
        m.setMovieId(4);
        m.setMovieName("Mission Imposible");
        m.setMovieProducer("Leonardo Di Caprio");
        m.setMovieDirector("Leo Di Caprio");
        m.setMovieClassification("B15");
        m.setMovieDuration(125);
//        u.setUserName("julio");
//        u.setUserPassword("pass");
//        u.setUserType(true);
  
        MovieJpaController mc = new MovieJpaController();
        UserJpaController uc = new UserJpaController();
        try {
//          pjc.create(m);
          mc.edit(m);
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
