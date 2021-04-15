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
import entities.Room;
import entities.Billboard;
import entities.Schedule;
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

    public void create(Schedule schedule) {
        if (schedule.getBillboardList() == null) {
            schedule.setBillboardList(new ArrayList<Billboard>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room roomId = schedule.getRoomId();
            if (roomId != null) {
                roomId = em.getReference(roomId.getClass(), roomId.getRoomId());
                schedule.setRoomId(roomId);
            }
            List<Billboard> attachedBillboardList = new ArrayList<Billboard>();
            for (Billboard billboardListBillboardToAttach : schedule.getBillboardList()) {
                billboardListBillboardToAttach = em.getReference(billboardListBillboardToAttach.getClass(), billboardListBillboardToAttach.getBillboardId());
                attachedBillboardList.add(billboardListBillboardToAttach);
            }
            schedule.setBillboardList(attachedBillboardList);
            em.persist(schedule);
            if (roomId != null) {
                roomId.getScheduleList().add(schedule);
                roomId = em.merge(roomId);
            }
            for (Billboard billboardListBillboard : schedule.getBillboardList()) {
                Schedule oldScheduleIdOfBillboardListBillboard = billboardListBillboard.getScheduleId();
                billboardListBillboard.setScheduleId(schedule);
                billboardListBillboard = em.merge(billboardListBillboard);
                if (oldScheduleIdOfBillboardListBillboard != null) {
                    oldScheduleIdOfBillboardListBillboard.getBillboardList().remove(billboardListBillboard);
                    oldScheduleIdOfBillboardListBillboard = em.merge(oldScheduleIdOfBillboardListBillboard);
                }
            }
            em.getTransaction().commit();
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
            Schedule persistentSchedule = em.find(Schedule.class, schedule.getScheduleId());
            Room roomIdOld = persistentSchedule.getRoomId();
            Room roomIdNew = schedule.getRoomId();
            List<Billboard> billboardListOld = persistentSchedule.getBillboardList();
            List<Billboard> billboardListNew = schedule.getBillboardList();
            List<String> illegalOrphanMessages = null;
            for (Billboard billboardListOldBillboard : billboardListOld) {
                if (!billboardListNew.contains(billboardListOldBillboard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Billboard " + billboardListOldBillboard + " since its scheduleId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roomIdNew != null) {
                roomIdNew = em.getReference(roomIdNew.getClass(), roomIdNew.getRoomId());
                schedule.setRoomId(roomIdNew);
            }
            List<Billboard> attachedBillboardListNew = new ArrayList<Billboard>();
            for (Billboard billboardListNewBillboardToAttach : billboardListNew) {
                billboardListNewBillboardToAttach = em.getReference(billboardListNewBillboardToAttach.getClass(), billboardListNewBillboardToAttach.getBillboardId());
                attachedBillboardListNew.add(billboardListNewBillboardToAttach);
            }
            billboardListNew = attachedBillboardListNew;
            schedule.setBillboardList(billboardListNew);
            schedule = em.merge(schedule);
            if (roomIdOld != null && !roomIdOld.equals(roomIdNew)) {
                roomIdOld.getScheduleList().remove(schedule);
                roomIdOld = em.merge(roomIdOld);
            }
            if (roomIdNew != null && !roomIdNew.equals(roomIdOld)) {
                roomIdNew.getScheduleList().add(schedule);
                roomIdNew = em.merge(roomIdNew);
            }
            for (Billboard billboardListNewBillboard : billboardListNew) {
                if (!billboardListOld.contains(billboardListNewBillboard)) {
                    Schedule oldScheduleIdOfBillboardListNewBillboard = billboardListNewBillboard.getScheduleId();
                    billboardListNewBillboard.setScheduleId(schedule);
                    billboardListNewBillboard = em.merge(billboardListNewBillboard);
                    if (oldScheduleIdOfBillboardListNewBillboard != null && !oldScheduleIdOfBillboardListNewBillboard.equals(schedule)) {
                        oldScheduleIdOfBillboardListNewBillboard.getBillboardList().remove(billboardListNewBillboard);
                        oldScheduleIdOfBillboardListNewBillboard = em.merge(oldScheduleIdOfBillboardListNewBillboard);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = schedule.getScheduleId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schedule schedule;
            try {
                schedule = em.getReference(Schedule.class, id);
                schedule.getScheduleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schedule with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Billboard> billboardListOrphanCheck = schedule.getBillboardList();
            for (Billboard billboardListOrphanCheckBillboard : billboardListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Schedule (" + schedule + ") cannot be destroyed since the Billboard " + billboardListOrphanCheckBillboard + " in its billboardList field has a non-nullable scheduleId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Room roomId = schedule.getRoomId();
            if (roomId != null) {
                roomId.getScheduleList().remove(schedule);
                roomId = em.merge(roomId);
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

    public Schedule findSchedule(Integer id) {
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
