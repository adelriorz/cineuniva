package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Municipality;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.State;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code for CRUD Municipality controler operations
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

    public void create(Municipality municipality) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            State stateId = municipality.getStateId();
            if (stateId != null) {
                stateId = em.getReference(stateId.getClass(), stateId.getStateId());
                municipality.setStateId(stateId);
            }
            em.persist(municipality);
            if (stateId != null) {
                stateId.getMunicipalityList().add(municipality);
                stateId = em.merge(stateId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipality municipality) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality persistentMunicipality = em.find(Municipality.class, municipality.getMunicipalityId());
            State stateIdOld = persistentMunicipality.getStateId();
            State stateIdNew = municipality.getStateId();
            if (stateIdNew != null) {
                stateIdNew = em.getReference(stateIdNew.getClass(), stateIdNew.getStateId());
                municipality.setStateId(stateIdNew);
            }
            municipality = em.merge(municipality);
            if (stateIdOld != null && !stateIdOld.equals(stateIdNew)) {
                stateIdOld.getMunicipalityList().remove(municipality);
                stateIdOld = em.merge(stateIdOld);
            }
            if (stateIdNew != null && !stateIdNew.equals(stateIdOld)) {
                stateIdNew.getMunicipalityList().add(municipality);
                stateIdNew = em.merge(stateIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipality.getMunicipalityId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality municipality;
            try {
                municipality = em.getReference(Municipality.class, id);
                municipality.getMunicipalityId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.", enfe);
            }
            State stateId = municipality.getStateId();
            if (stateId != null) {
                stateId.getMunicipalityList().remove(municipality);
                stateId = em.merge(stateId);
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

    public Municipality findMunicipality(Integer id) {
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
