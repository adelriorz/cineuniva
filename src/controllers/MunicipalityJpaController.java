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
import entities.State;
import entities.MovieLocation;
import entities.Municipality;
import entities.MunicipalityPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class MunicipalityJpaController implements Serializable {

    public MunicipalityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MunicipalityJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipality municipality) throws PreexistingEntityException, Exception {
        if (municipality.getMunicipalityPK() == null) {
            municipality.setMunicipalityPK(new MunicipalityPK());
        }
        if (municipality.getMovieLocationList() == null) {
            municipality.setMovieLocationList(new ArrayList<MovieLocation>());
        }
        municipality.getMunicipalityPK().setStateId(municipality.getState().getStateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            State state = municipality.getState();
            if (state != null) {
                state = em.getReference(state.getClass(), state.getStateId());
                municipality.setState(state);
            }
            List<MovieLocation> attachedMovieLocationList = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListMovieLocationToAttach : municipality.getMovieLocationList()) {
                movieLocationListMovieLocationToAttach = em.getReference(movieLocationListMovieLocationToAttach.getClass(), movieLocationListMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationList.add(movieLocationListMovieLocationToAttach);
            }
            municipality.setMovieLocationList(attachedMovieLocationList);
            em.persist(municipality);
            if (state != null) {
                state.getMunicipalityList().add(municipality);
                state = em.merge(state);
            }
            for (MovieLocation movieLocationListMovieLocation : municipality.getMovieLocationList()) {
                Municipality oldMunicipalityOfMovieLocationListMovieLocation = movieLocationListMovieLocation.getMunicipality();
                movieLocationListMovieLocation.setMunicipality(municipality);
                movieLocationListMovieLocation = em.merge(movieLocationListMovieLocation);
                if (oldMunicipalityOfMovieLocationListMovieLocation != null) {
                    oldMunicipalityOfMovieLocationListMovieLocation.getMovieLocationList().remove(movieLocationListMovieLocation);
                    oldMunicipalityOfMovieLocationListMovieLocation = em.merge(oldMunicipalityOfMovieLocationListMovieLocation);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMunicipality(municipality.getMunicipalityPK()) != null) {
                throw new PreexistingEntityException("Municipality " + municipality + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipality municipality) throws IllegalOrphanException, NonexistentEntityException, Exception {
        municipality.getMunicipalityPK().setStateId(municipality.getState().getStateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality persistentMunicipality = em.find(Municipality.class, municipality.getMunicipalityPK());
            State stateOld = persistentMunicipality.getState();
            State stateNew = municipality.getState();
            List<MovieLocation> movieLocationListOld = persistentMunicipality.getMovieLocationList();
            List<MovieLocation> movieLocationListNew = municipality.getMovieLocationList();
            List<String> illegalOrphanMessages = null;
            for (MovieLocation movieLocationListOldMovieLocation : movieLocationListOld) {
                if (!movieLocationListNew.contains(movieLocationListOldMovieLocation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovieLocation " + movieLocationListOldMovieLocation + " since its municipality field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (stateNew != null) {
                stateNew = em.getReference(stateNew.getClass(), stateNew.getStateId());
                municipality.setState(stateNew);
            }
            List<MovieLocation> attachedMovieLocationListNew = new ArrayList<MovieLocation>();
            for (MovieLocation movieLocationListNewMovieLocationToAttach : movieLocationListNew) {
                movieLocationListNewMovieLocationToAttach = em.getReference(movieLocationListNewMovieLocationToAttach.getClass(), movieLocationListNewMovieLocationToAttach.getMovieLocationPK());
                attachedMovieLocationListNew.add(movieLocationListNewMovieLocationToAttach);
            }
            movieLocationListNew = attachedMovieLocationListNew;
            municipality.setMovieLocationList(movieLocationListNew);
            municipality = em.merge(municipality);
            if (stateOld != null && !stateOld.equals(stateNew)) {
                stateOld.getMunicipalityList().remove(municipality);
                stateOld = em.merge(stateOld);
            }
            if (stateNew != null && !stateNew.equals(stateOld)) {
                stateNew.getMunicipalityList().add(municipality);
                stateNew = em.merge(stateNew);
            }
            for (MovieLocation movieLocationListNewMovieLocation : movieLocationListNew) {
                if (!movieLocationListOld.contains(movieLocationListNewMovieLocation)) {
                    Municipality oldMunicipalityOfMovieLocationListNewMovieLocation = movieLocationListNewMovieLocation.getMunicipality();
                    movieLocationListNewMovieLocation.setMunicipality(municipality);
                    movieLocationListNewMovieLocation = em.merge(movieLocationListNewMovieLocation);
                    if (oldMunicipalityOfMovieLocationListNewMovieLocation != null && !oldMunicipalityOfMovieLocationListNewMovieLocation.equals(municipality)) {
                        oldMunicipalityOfMovieLocationListNewMovieLocation.getMovieLocationList().remove(movieLocationListNewMovieLocation);
                        oldMunicipalityOfMovieLocationListNewMovieLocation = em.merge(oldMunicipalityOfMovieLocationListNewMovieLocation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MunicipalityPK id = municipality.getMunicipalityPK();
                if (findMunicipality(id) == null) {
                    throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MunicipalityPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality municipality;
            try {
                municipality = em.getReference(Municipality.class, id);
                municipality.getMunicipalityPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MovieLocation> movieLocationListOrphanCheck = municipality.getMovieLocationList();
            for (MovieLocation movieLocationListOrphanCheckMovieLocation : movieLocationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipality (" + municipality + ") cannot be destroyed since the MovieLocation " + movieLocationListOrphanCheckMovieLocation + " in its movieLocationList field has a non-nullable municipality field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            State state = municipality.getState();
            if (state != null) {
                state.getMunicipalityList().remove(municipality);
                state = em.merge(state);
            }
            em.remove(municipality);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipality> findMunicipalityEntities() {
        return findMunicipalityEntities(true, -1, -1);
    }

    public List<Municipality> findMunicipalityEntities(int maxResults, int firstResult) {
        return findMunicipalityEntities(false, maxResults, firstResult);
    }

    private List<Municipality> findMunicipalityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipality.class));
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

    public Municipality findMunicipality(MunicipalityPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipality.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipalityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipality> rt = cq.from(Municipality.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
