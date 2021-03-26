/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Assistance;
import entities.Movie;
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
        if (movie.getAssistanceList() == null) {
            movie.setAssistanceList(new ArrayList<Assistance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Assistance> attachedAssistanceList = new ArrayList<Assistance>();
            for (Assistance assistanceListAssistanceToAttach : movie.getAssistanceList()) {
                assistanceListAssistanceToAttach = em.getReference(assistanceListAssistanceToAttach.getClass(), assistanceListAssistanceToAttach.getAssistancePK());
                attachedAssistanceList.add(assistanceListAssistanceToAttach);
            }
            movie.setAssistanceList(attachedAssistanceList);
            em.persist(movie);
            for (Assistance assistanceListAssistance : movie.getAssistanceList()) {
                Movie oldMovieOfAssistanceListAssistance = assistanceListAssistance.getMovie();
                assistanceListAssistance.setMovie(movie);
                assistanceListAssistance = em.merge(assistanceListAssistance);
                if (oldMovieOfAssistanceListAssistance != null) {
                    oldMovieOfAssistanceListAssistance.getAssistanceList().remove(assistanceListAssistance);
                    oldMovieOfAssistanceListAssistance = em.merge(oldMovieOfAssistanceListAssistance);
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
            List<Assistance> assistanceListOld = persistentMovie.getAssistanceList();
            List<Assistance> assistanceListNew = movie.getAssistanceList();
            List<String> illegalOrphanMessages = null;
            for (Assistance assistanceListOldAssistance : assistanceListOld) {
                if (!assistanceListNew.contains(assistanceListOldAssistance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assistance " + assistanceListOldAssistance + " since its movie field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Assistance> attachedAssistanceListNew = new ArrayList<>();
            for (Assistance assistanceListNewAssistanceToAttach : assistanceListNew) {
                assistanceListNewAssistanceToAttach = em.getReference(assistanceListNewAssistanceToAttach.getClass(), assistanceListNewAssistanceToAttach.getAssistancePK());
                attachedAssistanceListNew.add(assistanceListNewAssistanceToAttach);
            }
            assistanceListNew = attachedAssistanceListNew;
            movie.setAssistanceList(assistanceListNew);
            movie = em.merge(movie);
            for (Assistance assistanceListNewAssistance : assistanceListNew) {
                if (!assistanceListOld.contains(assistanceListNewAssistance)) {
                    Movie oldMovieOfAssistanceListNewAssistance = assistanceListNewAssistance.getMovie();
                    assistanceListNewAssistance.setMovie(movie);
                    assistanceListNewAssistance = em.merge(assistanceListNewAssistance);
                    if (oldMovieOfAssistanceListNewAssistance != null && !oldMovieOfAssistanceListNewAssistance.equals(movie)) {
                        oldMovieOfAssistanceListNewAssistance.getAssistanceList().remove(assistanceListNewAssistance);
                        oldMovieOfAssistanceListNewAssistance = em.merge(oldMovieOfAssistanceListNewAssistance);
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
            List<Assistance> assistanceListOrphanCheck = movie.getAssistanceList();
            for (Assistance assistanceListOrphanCheckAssistance : assistanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the Assistance " + assistanceListOrphanCheckAssistance + " in its assistanceList field has a non-nullable movie field.");
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
