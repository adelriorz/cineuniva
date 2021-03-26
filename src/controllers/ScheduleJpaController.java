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
import entities.Assistance;
import entities.Schedule;
import entities.SchedulePK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armando Del Rio
 */
public class ScheduleJpaController implements Serializable {

    public ScheduleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ScheduleJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Schedule schedule) throws PreexistingEntityException, Exception {
        if (schedule.getSchedulePK() == null) {
            schedule.setSchedulePK(new SchedulePK());
        }
        if (schedule.getAssistanceList() == null) {
            schedule.setAssistanceList(new ArrayList<Assistance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Assistance> attachedAssistanceList = new ArrayList<Assistance>();
            for (Assistance assistanceListAssistanceToAttach : schedule.getAssistanceList()) {
                assistanceListAssistanceToAttach = em.getReference(assistanceListAssistanceToAttach.getClass(), assistanceListAssistanceToAttach.getAssistancePK());
                attachedAssistanceList.add(assistanceListAssistanceToAttach);
            }
            schedule.setAssistanceList(attachedAssistanceList);
            em.persist(schedule);
            for (Assistance assistanceListAssistance : schedule.getAssistanceList()) {
                Schedule oldScheduleOfAssistanceListAssistance = assistanceListAssistance.getSchedule();
                assistanceListAssistance.setSchedule(schedule);
                assistanceListAssistance = em.merge(assistanceListAssistance);
                if (oldScheduleOfAssistanceListAssistance != null) {
                    oldScheduleOfAssistanceListAssistance.getAssistanceList().remove(assistanceListAssistance);
                    oldScheduleOfAssistanceListAssistance = em.merge(oldScheduleOfAssistanceListAssistance);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSchedule(schedule.getSchedulePK()) != null) {
                throw new PreexistingEntityException("Schedule " + schedule + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Schedule schedule) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schedule persistentSchedule = em.find(Schedule.class, schedule.getSchedulePK());
            List<Assistance> assistanceListOld = persistentSchedule.getAssistanceList();
            List<Assistance> assistanceListNew = schedule.getAssistanceList();
            List<String> illegalOrphanMessages = null;
            for (Assistance assistanceListOldAssistance : assistanceListOld) {
                if (!assistanceListNew.contains(assistanceListOldAssistance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assistance " + assistanceListOldAssistance + " since its schedule field is not nullable.");
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
            schedule.setAssistanceList(assistanceListNew);
            schedule = em.merge(schedule);
            for (Assistance assistanceListNewAssistance : assistanceListNew) {
                if (!assistanceListOld.contains(assistanceListNewAssistance)) {
                    Schedule oldScheduleOfAssistanceListNewAssistance = assistanceListNewAssistance.getSchedule();
                    assistanceListNewAssistance.setSchedule(schedule);
                    assistanceListNewAssistance = em.merge(assistanceListNewAssistance);
                    if (oldScheduleOfAssistanceListNewAssistance != null && !oldScheduleOfAssistanceListNewAssistance.equals(schedule)) {
                        oldScheduleOfAssistanceListNewAssistance.getAssistanceList().remove(assistanceListNewAssistance);
                        oldScheduleOfAssistanceListNewAssistance = em.merge(oldScheduleOfAssistanceListNewAssistance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SchedulePK id = schedule.getSchedulePK();
                if (findSchedule(id) == null) {
                    throw new NonexistentEntityException("The schedule with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SchedulePK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schedule schedule;
            try {
                schedule = em.getReference(Schedule.class, id);
                schedule.getSchedulePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schedule with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assistance> assistanceListOrphanCheck = schedule.getAssistanceList();
            for (Assistance assistanceListOrphanCheckAssistance : assistanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Schedule (" + schedule + ") cannot be destroyed since the Assistance " + assistanceListOrphanCheckAssistance + " in its assistanceList field has a non-nullable schedule field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(schedule);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Schedule> findScheduleEntities() {
        return findScheduleEntities(true, -1, -1);
    }

    public List<Schedule> findScheduleEntities(int maxResults, int firstResult) {
        return findScheduleEntities(false, maxResults, firstResult);
    }

    private List<Schedule> findScheduleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Schedule.class));
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

    public Schedule findSchedule(SchedulePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Schedule.class, id);
        } finally {
            em.close();
        }
    }

    public int getScheduleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Schedule> rt = cq.from(Schedule.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
