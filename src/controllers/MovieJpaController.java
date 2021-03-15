/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.Movie;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.MovieSchedule;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class MovieJpaController implements Serializable {

    public MovieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MovieJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movie movie) {
        if (movie.getMovieScheduleList() == null) {
            movie.setMovieScheduleList(new ArrayList<MovieSchedule>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MovieSchedule> attachedMovieScheduleList = new ArrayList<MovieSchedule>();
            for (MovieSchedule movieScheduleListMovieScheduleToAttach : movie.getMovieScheduleList()) {
                movieScheduleListMovieScheduleToAttach = em.getReference(movieScheduleListMovieScheduleToAttach.getClass(), movieScheduleListMovieScheduleToAttach.getMovieSchedulePK());
                attachedMovieScheduleList.add(movieScheduleListMovieScheduleToAttach);
            }
            movie.setMovieScheduleList(attachedMovieScheduleList);
            em.persist(movie);
            for (MovieSchedule movieScheduleListMovieSchedule : movie.getMovieScheduleList()) {
                Movie oldMovieOfMovieScheduleListMovieSchedule = movieScheduleListMovieSchedule.getMovie();
                movieScheduleListMovieSchedule.setMovie(movie);
                movieScheduleListMovieSchedule = em.merge(movieScheduleListMovieSchedule);
                if (oldMovieOfMovieScheduleListMovieSchedule != null) {
                    oldMovieOfMovieScheduleListMovieSchedule.getMovieScheduleList().remove(movieScheduleListMovieSchedule);
                    oldMovieOfMovieScheduleListMovieSchedule = em.merge(oldMovieOfMovieScheduleListMovieSchedule);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movie movie) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie persistentMovie = em.find(Movie.class, movie.getMovieId());
            List<MovieSchedule> movieScheduleListOld = persistentMovie.getMovieScheduleList();
            List<MovieSchedule> movieScheduleListNew = movie.getMovieScheduleList();
            List<String> illegalOrphanMessages = null;
            for (MovieSchedule movieScheduleListOldMovieSchedule : movieScheduleListOld) {
                if (!movieScheduleListNew.contains(movieScheduleListOldMovieSchedule)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovieSchedule " + movieScheduleListOldMovieSchedule + " since its movie field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MovieSchedule> attachedMovieScheduleListNew = new ArrayList<MovieSchedule>();
            for (MovieSchedule movieScheduleListNewMovieScheduleToAttach : movieScheduleListNew) {
                movieScheduleListNewMovieScheduleToAttach = em.getReference(movieScheduleListNewMovieScheduleToAttach.getClass(), movieScheduleListNewMovieScheduleToAttach.getMovieSchedulePK());
                attachedMovieScheduleListNew.add(movieScheduleListNewMovieScheduleToAttach);
            }
            movieScheduleListNew = attachedMovieScheduleListNew;
            movie.setMovieScheduleList(movieScheduleListNew);
            movie = em.merge(movie);
            for (MovieSchedule movieScheduleListNewMovieSchedule : movieScheduleListNew) {
                if (!movieScheduleListOld.contains(movieScheduleListNewMovieSchedule)) {
                    Movie oldMovieOfMovieScheduleListNewMovieSchedule = movieScheduleListNewMovieSchedule.getMovie();
                    movieScheduleListNewMovieSchedule.setMovie(movie);
                    movieScheduleListNewMovieSchedule = em.merge(movieScheduleListNewMovieSchedule);
                    if (oldMovieOfMovieScheduleListNewMovieSchedule != null && !oldMovieOfMovieScheduleListNewMovieSchedule.equals(movie)) {
                        oldMovieOfMovieScheduleListNewMovieSchedule.getMovieScheduleList().remove(movieScheduleListNewMovieSchedule);
                        oldMovieOfMovieScheduleListNewMovieSchedule = em.merge(oldMovieOfMovieScheduleListNewMovieSchedule);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movie.getMovieId();
                if (findMovie(id) == null) {
                    throw new NonexistentEntityException("The movie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movie;
            try {
                movie = em.getReference(Movie.class, id);
                movie.getMovieId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movie with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MovieSchedule> movieScheduleListOrphanCheck = movie.getMovieScheduleList();
            for (MovieSchedule movieScheduleListOrphanCheckMovieSchedule : movieScheduleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the MovieSchedule " + movieScheduleListOrphanCheckMovieSchedule + " in its movieScheduleList field has a non-nullable movie field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(movie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movie> findMovieEntities() {
        return findMovieEntities(true, -1, -1);
    }

    public List<Movie> findMovieEntities(int maxResults, int firstResult) {
        return findMovieEntities(false, maxResults, firstResult);
    }

    private List<Movie> findMovieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movie.class));
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

    public Movie findMovie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movie> rt = cq.from(Movie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
