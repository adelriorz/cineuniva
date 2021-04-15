/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Assistance;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Billboard;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class AssistanceJpaController implements Serializable {

    public AssistanceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AssistanceJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Assistance assistance) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Billboard billboardId = assistance.getBillboardId();
            if (billboardId != null) {
                billboardId = em.getReference(billboardId.getClass(), billboardId.getBillboardId());
                assistance.setBillboardId(billboardId);
            }
            User userId = assistance.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                assistance.setUserId(userId);
            }
            em.persist(assistance);
            if (billboardId != null) {
                billboardId.getAssistanceList().add(assistance);
                billboardId = em.merge(billboardId);
            }
            if (userId != null) {
                userId.getAssistanceList().add(assistance);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Assistance assistance) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assistance persistentAssistance = em.find(Assistance.class, assistance.getAssistanceId());
            Billboard billboardIdOld = persistentAssistance.getBillboardId();
            Billboard billboardIdNew = assistance.getBillboardId();
            User userIdOld = persistentAssistance.getUserId();
            User userIdNew = assistance.getUserId();
            if (billboardIdNew != null) {
                billboardIdNew = em.getReference(billboardIdNew.getClass(), billboardIdNew.getBillboardId());
                assistance.setBillboardId(billboardIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                assistance.setUserId(userIdNew);
            }
            assistance = em.merge(assistance);
            if (billboardIdOld != null && !billboardIdOld.equals(billboardIdNew)) {
                billboardIdOld.getAssistanceList().remove(assistance);
                billboardIdOld = em.merge(billboardIdOld);
            }
            if (billboardIdNew != null && !billboardIdNew.equals(billboardIdOld)) {
                billboardIdNew.getAssistanceList().add(assistance);
                billboardIdNew = em.merge(billboardIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getAssistanceList().remove(assistance);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getAssistanceList().add(assistance);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = assistance.getAssistanceId();
                if (findAssistance(id) == null) {
                    throw new NonexistentEntityException("The assistance with id " + id + " no longer exists.");
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
            Assistance assistance;
            try {
                assistance = em.getReference(Assistance.class, id);
                assistance.getAssistanceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assistance with id " + id + " no longer exists.", enfe);
            }
            Billboard billboardId = assistance.getBillboardId();
            if (billboardId != null) {
                billboardId.getAssistanceList().remove(assistance);
                billboardId = em.merge(billboardId);
            }
            User userId = assistance.getUserId();
            if (userId != null) {
                userId.getAssistanceList().remove(assistance);
                userId = em.merge(userId);
            }
            em.remove(assistance);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Assistance> findAssistanceEntities() {
        return findAssistanceEntities(true, -1, -1);
    }

    public List<Assistance> findAssistanceEntities(int maxResults, int firstResult) {
        return findAssistanceEntities(false, maxResults, firstResult);
    }

    private List<Assistance> findAssistanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Assistance.class));
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

    public Assistance findAssistance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Assistance.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssistanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Assistance> rt = cq.from(Assistance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
