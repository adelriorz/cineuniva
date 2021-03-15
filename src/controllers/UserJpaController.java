/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.MovieLocation;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public UserJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getMovieLocationList() == null) {
            user.setMovieLocationList(new ArrayList<MovieLocation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MovieLocation> attachedMovieLocationList = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListMovieLocationToAttach : user.getMovieLocationList()) {
                movieLocationListMovieLocationToAttach = em.getReference(movieLocationListMovieLocationToAttach.getClass(), movieLocationListMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationList.add(movieLocationListMovieLocationToAttach);
            }
            user.setMovieLocationList(attachedMovieLocationList);
            em.persist(user);
            for (MovieLocation movieLocationListMovieLocation : user.getMovieLocationList()) {
                movieLocationListMovieLocation.getUserList().add(user);
                movieLocationListMovieLocation = em.merge(movieLocationListMovieLocation);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            List<MovieLocation> movieLocationListOld = persistentUser.getMovieLocationList();
            List<MovieLocation> movieLocationListNew = user.getMovieLocationList();
            List<MovieLocation> attachedMovieLocationListNew = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListNewMovieLocationToAttach : movieLocationListNew) {
                movieLocationListNewMovieLocationToAttach = em.getReference(movieLocationListNewMovieLocationToAttach.getClass(), movieLocationListNewMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationListNew.add(movieLocationListNewMovieLocationToAttach);
            }
            movieLocationListNew = attachedMovieLocationListNew;
            user.setMovieLocationList(movieLocationListNew);
            user = em.merge(user);
            for (MovieLocation movieLocationListOldMovieLocation : movieLocationListOld) {
                if (!movieLocationListNew.contains(movieLocationListOldMovieLocation)) {
                    movieLocationListOldMovieLocation.getUserList().remove(user);
                    movieLocationListOldMovieLocation = em.merge(movieLocationListOldMovieLocation);
                }
            }
            for (MovieLocation movieLocationListNewMovieLocation : movieLocationListNew) {
                if (!movieLocationListOld.contains(movieLocationListNewMovieLocation)) {
                    movieLocationListNewMovieLocation.getUserList().add(user);
                    movieLocationListNewMovieLocation = em.merge(movieLocationListNewMovieLocation);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<MovieLocation> movieLocationList = user.getMovieLocationList();
            for (MovieLocation movieLocationListMovieLocation : movieLocationList) {
                movieLocationListMovieLocation.getUserList().remove(user);
                movieLocationListMovieLocation = em.merge(movieLocationListMovieLocation);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
