/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Movie;
import entities.Schedule;
import entities.MovieLocation;
import entities.MovieSchedule;
import entities.MovieSchedulePK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class MovieScheduleJpaController implements Serializable {

    public MovieScheduleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MovieScheduleJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovieSchedule movieSchedule) throws PreexistingEntityException, Exception {
        if (movieSchedule.getMovieSchedulePK() == null) {
            movieSchedule.setMovieSchedulePK(new MovieSchedulePK());
        }
        if (movieSchedule.getMovieLocationList() == null) {
            movieSchedule.setMovieLocationList(new ArrayList<MovieLocation>());
        }
        movieSchedule.getMovieSchedulePK().setScheduleIdfk(movieSchedule.getSchedule().getSchedulePK().getScheduleId());
        movieSchedule.getMovieSchedulePK().setRoomIdfk(movieSchedule.getSchedule().getSchedulePK().getRoomId());
        movieSchedule.getMovieSchedulePK().setMovieIdfk(movieSchedule.getMovie().getMovieId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movie = movieSchedule.getMovie();
            if (movie != null) {
                movie = em.getReference(movie.getClass(), movie.getMovieId());
                movieSchedule.setMovie(movie);
            }
            Schedule schedule = movieSchedule.getSchedule();
            if (schedule != null) {
                schedule = em.getReference(schedule.getClass(), schedule.getSchedulePK());
                movieSchedule.setSchedule(schedule);
            }
            List<MovieLocation> attachedMovieLocationList = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListMovieLocationToAttach : movieSchedule.getMovieLocationList()) {
                movieLocationListMovieLocationToAttach = em.getReference(movieLocationListMovieLocationToAttach.getClass(), movieLocationListMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationList.add(movieLocationListMovieLocationToAttach);
            }
            movieSchedule.setMovieLocationList(attachedMovieLocationList);
            em.persist(movieSchedule);
            if (movie != null) {
                movie.getMovieScheduleList().add(movieSchedule);
                movie = em.merge(movie);
            }
            if (schedule != null) {
                schedule.getMovieScheduleList().add(movieSchedule);
                schedule = em.merge(schedule);
            }
            for (MovieLocation movieLocationListMovieLocation : movieSchedule.getMovieLocationList()) {
                MovieSchedule oldMovieScheduleOfMovieLocationListMovieLocation = movieLocationListMovieLocation.getMovieSchedule();
                movieLocationListMovieLocation.setMovieSchedule(movieSchedule);
                movieLocationListMovieLocation = em.merge(movieLocationListMovieLocation);
                if (oldMovieScheduleOfMovieLocationListMovieLocation != null) {
                    oldMovieScheduleOfMovieLocationListMovieLocation.getMovieLocationList().remove(movieLocationListMovieLocation);
                    oldMovieScheduleOfMovieLocationListMovieLocation = em.merge(oldMovieScheduleOfMovieLocationListMovieLocation);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovieSchedule(movieSchedule.getMovieSchedulePK()) != null) {
                throw new PreexistingEntityException("MovieSchedule " + movieSchedule + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovieSchedule movieSchedule) throws IllegalOrphanException, NonexistentEntityException, Exception {
        movieSchedule.getMovieSchedulePK().setScheduleIdfk(movieSchedule.getSchedule().getSchedulePK().getScheduleId());
        movieSchedule.getMovieSchedulePK().setRoomIdfk(movieSchedule.getSchedule().getSchedulePK().getRoomId());
        movieSchedule.getMovieSchedulePK().setMovieIdfk(movieSchedule.getMovie().getMovieId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovieSchedule persistentMovieSchedule = em.find(MovieSchedule.class, movieSchedule.getMovieSchedulePK());
            Movie movieOld = persistentMovieSchedule.getMovie();
            Movie movieNew = movieSchedule.getMovie();
            Schedule scheduleOld = persistentMovieSchedule.getSchedule();
            Schedule scheduleNew = movieSchedule.getSchedule();
            List<MovieLocation> movieLocationListOld = persistentMovieSchedule.getMovieLocationList();
            List<MovieLocation> movieLocationListNew = movieSchedule.getMovieLocationList();
            List<String> illegalOrphanMessages = null;
            for (MovieLocation movieLocationListOldMovieLocation : movieLocationListOld) {
                if (!movieLocationListNew.contains(movieLocationListOldMovieLocation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovieLocation " + movieLocationListOldMovieLocation + " since its movieSchedule field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movieNew != null) {
                movieNew = em.getReference(movieNew.getClass(), movieNew.getMovieId());
                movieSchedule.setMovie(movieNew);
            }
            if (scheduleNew != null) {
                scheduleNew = em.getReference(scheduleNew.getClass(), scheduleNew.getSchedulePK());
                movieSchedule.setSchedule(scheduleNew);
            }
            List<MovieLocation> attachedMovieLocationListNew = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListNewMovieLocationToAttach : movieLocationListNew) {
                movieLocationListNewMovieLocationToAttach = em.getReference(movieLocationListNewMovieLocationToAttach.getClass(), movieLocationListNewMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationListNew.add(movieLocationListNewMovieLocationToAttach);
            }
            movieLocationListNew = attachedMovieLocationListNew;
            movieSchedule.setMovieLocationList(movieLocationListNew);
            movieSchedule = em.merge(movieSchedule);
            if (movieOld != null && !movieOld.equals(movieNew)) {
                movieOld.getMovieScheduleList().remove(movieSchedule);
                movieOld = em.merge(movieOld);
            }
            if (movieNew != null && !movieNew.equals(movieOld)) {
                movieNew.getMovieScheduleList().add(movieSchedule);
                movieNew = em.merge(movieNew);
            }
            if (scheduleOld != null && !scheduleOld.equals(scheduleNew)) {
                scheduleOld.getMovieScheduleList().remove(movieSchedule);
                scheduleOld = em.merge(scheduleOld);
            }
            if (scheduleNew != null && !scheduleNew.equals(scheduleOld)) {
                scheduleNew.getMovieScheduleList().add(movieSchedule);
                scheduleNew = em.merge(scheduleNew);
            }
            for (MovieLocation movieLocationListNewMovieLocation : movieLocationListNew) {
                if (!movieLocationListOld.contains(movieLocationListNewMovieLocation)) {
                    MovieSchedule oldMovieScheduleOfMovieLocationListNewMovieLocation = movieLocationListNewMovieLocation.getMovieSchedule();
                    movieLocationListNewMovieLocation.setMovieSchedule(movieSchedule);
                    movieLocationListNewMovieLocation = em.merge(movieLocationListNewMovieLocation);
                    if (oldMovieScheduleOfMovieLocationListNewMovieLocation != null && !oldMovieScheduleOfMovieLocationListNewMovieLocation.equals(movieSchedule)) {
                        oldMovieScheduleOfMovieLocationListNewMovieLocation.getMovieLocationList().remove(movieLocationListNewMovieLocation);
                        oldMovieScheduleOfMovieLocationListNewMovieLocation = em.merge(oldMovieScheduleOfMovieLocationListNewMovieLocation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MovieSchedulePK id = movieSchedule.getMovieSchedulePK();
                if (findMovieSchedule(id) == null) {
                    throw new NonexistentEntityException("The movieSchedule with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MovieSchedulePK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovieSchedule movieSchedule;
            try {
                movieSchedule = em.getReference(MovieSchedule.class, id);
                movieSchedule.getMovieSchedulePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movieSchedule with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MovieLocation> movieLocationListOrphanCheck = movieSchedule.getMovieLocationList();
            for (MovieLocation movieLocationListOrphanCheckMovieLocation : movieLocationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MovieSchedule (" + movieSchedule + ") cannot be destroyed since the MovieLocation " + movieLocationListOrphanCheckMovieLocation + " in its movieLocationList field has a non-nullable movieSchedule field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Movie movie = movieSchedule.getMovie();
            if (movie != null) {
                movie.getMovieScheduleList().remove(movieSchedule);
                movie = em.merge(movie);
            }
            Schedule schedule = movieSchedule.getSchedule();
            if (schedule != null) {
                schedule.getMovieScheduleList().remove(movieSchedule);
                schedule = em.merge(schedule);
            }
            em.remove(movieSchedule);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovieSchedule> findMovieScheduleEntities() {
        return findMovieScheduleEntities(true, -1, -1);
    }

    public List<MovieSchedule> findMovieScheduleEntities(int maxResults, int firstResult) {
        return findMovieScheduleEntities(false, maxResults, firstResult);
    }

    private List<MovieSchedule> findMovieScheduleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MovieSchedule.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovieSchedule findMovieSchedule(MovieSchedulePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovieSchedule.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieScheduleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MovieSchedule> rt = cq.from(MovieSchedule.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
