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
import entities.Billboard;
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
        if (movie.getBillboardList() == null) {
            movie.setBillboardList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Billboard> attachedBillboardList = new ArrayList<>();
            for (Billboard billboardListBillboardToAttach : movie.getBillboardList()) {
                billboardListBillboardToAttach = em.getReference(billboardListBillboardToAttach.getClass(), billboardListBillboardToAttach.getBillboardId());
                attachedBillboardList.add(billboardListBillboardToAttach);
            }
            movie.setBillboardList(attachedBillboardList);
            em.persist(movie);
            for (Billboard billboardListBillboard : movie.getBillboardList()) {
                Movie oldMovieIdOfBillboardListBillboard = billboardListBillboard.getMovieId();
                billboardListBillboard.setMovieId(movie);
                billboardListBillboard = em.merge(billboardListBillboard);
                if (oldMovieIdOfBillboardListBillboard != null) {
                    oldMovieIdOfBillboardListBillboard.getBillboardList().remove(billboardListBillboard);
                    oldMovieIdOfBillboardListBillboard = em.merge(oldMovieIdOfBillboardListBillboard);
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
            List<Billboard> billboardListOld = persistentMovie.getBillboardList();
            List<Billboard> billboardListNew = movie.getBillboardList();
            List<String> illegalOrphanMessages = null;
            for (Billboard billboardListOldBillboard : billboardListOld) {
                if (!billboardListNew.contains(billboardListOldBillboard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Billboard " + billboardListOldBillboard + " since its movieId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Billboard> attachedBillboardListNew = new ArrayList<>();
            for (Billboard billboardListNewBillboardToAttach : billboardListNew) {
                billboardListNewBillboardToAttach = em.getReference(billboardListNewBillboardToAttach.getClass(), billboardListNewBillboardToAttach.getBillboardId());
                attachedBillboardListNew.add(billboardListNewBillboardToAttach);
            }
            billboardListNew = attachedBillboardListNew;
            movie.setBillboardList(billboardListNew);
            movie = em.merge(movie);
            for (Billboard billboardListNewBillboard : billboardListNew) {
                if (!billboardListOld.contains(billboardListNewBillboard)) {
                    Movie oldMovieIdOfBillboardListNewBillboard = billboardListNewBillboard.getMovieId();
                    billboardListNewBillboard.setMovieId(movie);
                    billboardListNewBillboard = em.merge(billboardListNewBillboard);
                    if (oldMovieIdOfBillboardListNewBillboard != null && !oldMovieIdOfBillboardListNewBillboard.equals(movie)) {
                        oldMovieIdOfBillboardListNewBillboard.getBillboardList().remove(billboardListNewBillboard);
                        oldMovieIdOfBillboardListNewBillboard = em.merge(oldMovieIdOfBillboardListNewBillboard);
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
            List<Billboard> billboardListOrphanCheck = movie.getBillboardList();
            for (Billboard billboardListOrphanCheckBillboard : billboardListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the Billboard " + billboardListOrphanCheckBillboard + " in its billboardList field has a non-nullable movieId field.");
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
