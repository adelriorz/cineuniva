/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import entities.Movie;
import entities.User;
import controllers.MovieJpaController;
import controllers.UserJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;

/**
 *
 * @author Armando Del Rio
 */

//    public MovieJpaController() {
//        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
//    }

public class Tests {
    public static void main(String[] args) {
//        Movie m = new Movie();
//        User u = new User();
//        m.setMovieName("Lilo & Stitch");
//        m.setMovieProducer("Leonardo Di Caprio");
//        m.setMovieDirector("Leo Di Caprio");
//        m.setMovieClassification("B15");
//        m.setMovieDuration(125);
//        u.setUserName("Paola");
//        u.setUserPassword("3");
//        u.setUserType(false);
  
//        MovieJpaController pjc = new MovieJpaController();
//        UserJpaController ujc = new UserJpaController();


        try {
//            pjc.create(m);
//          ujc.create(u);
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
