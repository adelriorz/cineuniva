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
        if (user.getAssistanceList() == null) {
            user.setAssistanceList(new ArrayList<Assistance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Assistance> attachedAssistanceList = new ArrayList<Assistance>();
            for (Assistance assistanceListAssistanceToAttach : user.getAssistanceList()) {
                assistanceListAssistanceToAttach = em.getReference(assistanceListAssistanceToAttach.getClass(), assistanceListAssistanceToAttach.getAssistancePK());
                attachedAssistanceList.add(assistanceListAssistanceToAttach);
            }
            user.setAssistanceList(attachedAssistanceList);
            em.persist(user);
            for (Assistance assistanceListAssistance : user.getAssistanceList()) {
                User oldUserOfAssistanceListAssistance = assistanceListAssistance.getUser();
                assistanceListAssistance.setUser(user);
                assistanceListAssistance = em.merge(assistanceListAssistance);
                if (oldUserOfAssistanceListAssistance != null) {
                    oldUserOfAssistanceListAssistance.getAssistanceList().remove(assistanceListAssistance);
                    oldUserOfAssistanceListAssistance = em.merge(oldUserOfAssistanceListAssistance);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            List<Assistance> assistanceListOld = persistentUser.getAssistanceList();
            List<Assistance> assistanceListNew = user.getAssistanceList();
            List<String> illegalOrphanMessages = null;
            for (Assistance assistanceListOldAssistance : assistanceListOld) {
                if (!assistanceListNew.contains(assistanceListOldAssistance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assistance " + assistanceListOldAssistance + " since its user field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Assistance> attachedAssistanceListNew = new ArrayList<Assistance>();
            for (Assistance assistanceListNewAssistanceToAttach : assistanceListNew) {
                assistanceListNewAssistanceToAttach = em.getReference(assistanceListNewAssistanceToAttach.getClass(), assistanceListNewAssistanceToAttach.getAssistancePK());
                attachedAssistanceListNew.add(assistanceListNewAssistanceToAttach);
            }
            assistanceListNew = attachedAssistanceListNew;
            user.setAssistanceList(assistanceListNew);
            user = em.merge(user);
            for (Assistance assistanceListNewAssistance : assistanceListNew) {
                if (!assistanceListOld.contains(assistanceListNewAssistance)) {
                    User oldUserOfAssistanceListNewAssistance = assistanceListNewAssistance.getUser();
                    assistanceListNewAssistance.setUser(user);
                    assistanceListNewAssistance = em.merge(assistanceListNewAssistance);
                    if (oldUserOfAssistanceListNewAssistance != null && !oldUserOfAssistanceListNewAssistance.equals(user)) {
                        oldUserOfAssistanceListNewAssistance.getAssistanceList().remove(assistanceListNewAssistance);
                        oldUserOfAssistanceListNewAssistance = em.merge(oldUserOfAssistanceListNewAssistance);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Assistance> assistanceListOrphanCheck = user.getAssistanceList();
            for (Assistance assistanceListOrphanCheckAssistance : assistanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Assistance " + assistanceListOrphanCheckAssistance + " in its assistanceList field has a non-nullable user field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
