/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entities.MovieLocation;
import entities.MovieLocationPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.MovieSchedule;
import entities.Municipality;
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
public class MovieLocationJpaController implements Serializable {

    public MovieLocationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MovieLocationJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovieLocation movieLocation) throws PreexistingEntityException, Exception {
        if (movieLocation.getMovieLocationPK() == null) {
            movieLocation.setMovieLocationPK(new MovieLocationPK());
        }
        if (movieLocation.getUserList() == null) {
            movieLocation.setUserList(new ArrayList<User>());
        }
        movieLocation.getMovieLocationPK().setMunicipalityIdfk(movieLocation.getMunicipality().getMunicipalityPK().getMunicipalityId());
        movieLocation.getMovieLocationPK().setMovieIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getMovieIdfk());
        movieLocation.getMovieLocationPK().setStateIdfk(movieLocation.getMunicipality().getMunicipalityPK().getStateId());
        movieLocation.getMovieLocationPK().setRoomIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getRoomIdfk());
        movieLocation.getMovieLocationPK().setScheduleIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getScheduleIdfk());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovieSchedule movieSchedule = movieLocation.getMovieSchedule();
            if (movieSchedule != null) {
                movieSchedule = em.getReference(movieSchedule.getClass(), movieSchedule.getMovieSchedulePK());
                movieLocation.setMovieSchedule(movieSchedule);
            }
            Municipality municipality = movieLocation.getMunicipality();
            if (municipality != null) {
                municipality = em.getReference(municipality.getClass(), municipality.getMunicipalityPK());
                movieLocation.setMunicipality(municipality);
            }
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : movieLocation.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserId());
                attachedUserList.add(userListUserToAttach);
            }
            movieLocation.setUserList(attachedUserList);
            em.persist(movieLocation);
            if (movieSchedule != null) {
                movieSchedule.getMovieLocationList().add(movieLocation);
                movieSchedule = em.merge(movieSchedule);
            }
            if (municipality != null) {
                municipality.getMovieLocationList().add(movieLocation);
                municipality = em.merge(municipality);
            }
            for (User userListUser : movieLocation.getUserList()) {
                userListUser.getMovieLocationList().add(movieLocation);
                userListUser = em.merge(userListUser);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovieLocation(movieLocation.getMovieLocationPK()) != null) {
                throw new PreexistingEntityException("MovieLocation " + movieLocation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovieLocation movieLocation) throws NonexistentEntityException, Exception {
        movieLocation.getMovieLocationPK().setMunicipalityIdfk(movieLocation.getMunicipality().getMunicipalityPK().getMunicipalityId());
        movieLocation.getMovieLocationPK().setMovieIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getMovieIdfk());
        movieLocation.getMovieLocationPK().setStateIdfk(movieLocation.getMunicipality().getMunicipalityPK().getStateId());
        movieLocation.getMovieLocationPK().setRoomIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getRoomIdfk());
        movieLocation.getMovieLocationPK().setScheduleIdfk(movieLocation.getMovieSchedule().getMovieSchedulePK().getScheduleIdfk());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovieLocation persistentMovieLocation = em.find(MovieLocation.class, movieLocation.getMovieLocationPK());
            MovieSchedule movieScheduleOld = persistentMovieLocation.getMovieSchedule();
            MovieSchedule movieScheduleNew = movieLocation.getMovieSchedule();
            Municipality municipalityOld = persistentMovieLocation.getMunicipality();
            Municipality municipalityNew = movieLocation.getMunicipality();
            List<User> userListOld = persistentMovieLocation.getUserList();
            List<User> userListNew = movieLocation.getUserList();
            if (movieScheduleNew != null) {
                movieScheduleNew = em.getReference(movieScheduleNew.getClass(), movieScheduleNew.getMovieSchedulePK());
                movieLocation.setMovieSchedule(movieScheduleNew);
            }
            if (municipalityNew != null) {
                municipalityNew = em.getReference(municipalityNew.getClass(), municipalityNew.getMunicipalityPK());
                movieLocation.setMunicipality(municipalityNew);
            }
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserId());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            movieLocation.setUserList(userListNew);
            movieLocation = em.merge(movieLocation);
            if (movieScheduleOld != null && !movieScheduleOld.equals(movieScheduleNew)) {
                movieScheduleOld.getMovieLocationList().remove(movieLocation);
                movieScheduleOld = em.merge(movieScheduleOld);
            }
            if (movieScheduleNew != null && !movieScheduleNew.equals(movieScheduleOld)) {
                movieScheduleNew.getMovieLocationList().add(movieLocation);
                movieScheduleNew = em.merge(movieScheduleNew);
            }
            if (municipalityOld != null && !municipalityOld.equals(municipalityNew)) {
                municipalityOld.getMovieLocationList().remove(movieLocation);
                municipalityOld = em.merge(municipalityOld);
            }
            if (municipalityNew != null && !municipalityNew.equals(municipalityOld)) {
                municipalityNew.getMovieLocationList().add(movieLocation);
                municipalityNew = em.merge(municipalityNew);
            }
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getMovieLocationList().remove(movieLocation);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getMovieLocationList().add(movieLocation);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MovieLocationPK id = movieLocation.getMovieLocationPK();
                if (findMovieLocation(id) == null) {
                    throw new NonexistentEntityException("The movieLocation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MovieLocationPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovieLocation movieLocation;
            try {
                movieLocation = em.getReference(MovieLocation.class, id);
                movieLocation.getMovieLocationPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movieLocation with id " + id + " no longer exists.", enfe);
            }
            MovieSchedule movieSchedule = movieLocation.getMovieSchedule();
            if (movieSchedule != null) {
                movieSchedule.getMovieLocationList().remove(movieLocation);
                movieSchedule = em.merge(movieSchedule);
            }
            Municipality municipality = movieLocation.getMunicipality();
            if (municipality != null) {
                municipality.getMovieLocationList().remove(movieLocation);
                municipality = em.merge(municipality);
            }
            List<User> userList = movieLocation.getUserList();
            for (User userListUser : userList) {
                userListUser.getMovieLocationList().remove(movieLocation);
                userListUser = em.merge(userListUser);
            }
            em.remove(movieLocation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovieLocation> findMovieLocationEntities() {
        return findMovieLocationEntities(true, -1, -1);
    }

    public List<MovieLocation> findMovieLocationEntities(int maxResults, int firstResult) {
        return findMovieLocationEntities(false, maxResults, firstResult);
    }

    private List<MovieLocation> findMovieLocationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MovieLocation.class));
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

    public MovieLocation findMovieLocation(MovieLocationPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovieLocation.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieLocationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MovieLocation> rt = cq.from(MovieLocation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
