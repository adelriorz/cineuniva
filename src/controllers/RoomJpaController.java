package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Schedule;
import java.util.ArrayList;
import java.util.List;
import entities.Billboard;
import entities.Room;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code for CRUD Room controler operations
*/
public class RoomJpaController implements Serializable {

    public RoomJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RoomJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Room room) {
        if (room.getScheduleList() == null) {
            room.setScheduleList(new ArrayList<Schedule>());
        }
        if (room.getBillboardList() == null) {
            room.setBillboardList(new ArrayList<Billboard>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Schedule> attachedScheduleList = new ArrayList<Schedule>();
            for (Schedule scheduleListScheduleToAttach : room.getScheduleList()) {
                scheduleListScheduleToAttach = em.getReference(scheduleListScheduleToAttach.getClass(), scheduleListScheduleToAttach.getScheduleId());
                attachedScheduleList.add(scheduleListScheduleToAttach);
            }
            room.setScheduleList(attachedScheduleList);
            List<Billboard> attachedBillboardList = new ArrayList<Billboard>();
            for (Billboard billboardListBillboardToAttach : room.getBillboardList()) {
                billboardListBillboardToAttach = em.getReference(billboardListBillboardToAttach.getClass(), billboardListBillboardToAttach.getBillboardId());
                attachedBillboardList.add(billboardListBillboardToAttach);
            }
            room.setBillboardList(attachedBillboardList);
            em.persist(room);
            for (Schedule scheduleListSchedule : room.getScheduleList()) {
                Room oldRoomIdOfScheduleListSchedule = scheduleListSchedule.getRoomId();
                scheduleListSchedule.setRoomId(room);
                scheduleListSchedule = em.merge(scheduleListSchedule);
                if (oldRoomIdOfScheduleListSchedule != null) {
                    oldRoomIdOfScheduleListSchedule.getScheduleList().remove(scheduleListSchedule);
                    oldRoomIdOfScheduleListSchedule = em.merge(oldRoomIdOfScheduleListSchedule);
                }
            }
            for (Billboard billboardListBillboard : room.getBillboardList()) {
                Room oldRoomIdOfBillboardListBillboard = billboardListBillboard.getRoomId();
                billboardListBillboard.setRoomId(room);
                billboardListBillboard = em.merge(billboardListBillboard);
                if (oldRoomIdOfBillboardListBillboard != null) {
                    oldRoomIdOfBillboardListBillboard.getBillboardList().remove(billboardListBillboard);
                    oldRoomIdOfBillboardListBillboard = em.merge(oldRoomIdOfBillboardListBillboard);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Room room) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room persistentRoom = em.find(Room.class, room.getRoomId());
            List<Schedule> scheduleListOld = persistentRoom.getScheduleList();
            List<Schedule> scheduleListNew = room.getScheduleList();
            List<Billboard> billboardListOld = persistentRoom.getBillboardList();
            List<Billboard> billboardListNew = room.getBillboardList();
            List<String> illegalOrphanMessages = null;
            for (Schedule scheduleListOldSchedule : scheduleListOld) {
                if (!scheduleListNew.contains(scheduleListOldSchedule)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Schedule " + scheduleListOldSchedule + " since its roomId field is not nullable.");
                }
            }
            for (Billboard billboardListOldBillboard : billboardListOld) {
                if (!billboardListNew.contains(billboardListOldBillboard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Billboard " + billboardListOldBillboard + " since its roomId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Schedule> attachedScheduleListNew = new ArrayList<Schedule>();
            for (Schedule scheduleListNewScheduleToAttach : scheduleListNew) {
                scheduleListNewScheduleToAttach = em.getReference(scheduleListNewScheduleToAttach.getClass(), scheduleListNewScheduleToAttach.getScheduleId());
                attachedScheduleListNew.add(scheduleListNewScheduleToAttach);
            }
            scheduleListNew = attachedScheduleListNew;
            room.setScheduleList(scheduleListNew);
            List<Billboard> attachedBillboardListNew = new ArrayList<Billboard>();
            for (Billboard billboardListNewBillboardToAttach : billboardListNew) {
                billboardListNewBillboardToAttach = em.getReference(billboardListNewBillboardToAttach.getClass(), billboardListNewBillboardToAttach.getBillboardId());
                attachedBillboardListNew.add(billboardListNewBillboardToAttach);
            }
            billboardListNew = attachedBillboardListNew;
            room.setBillboardList(billboardListNew);
            room = em.merge(room);
            for (Schedule scheduleListNewSchedule : scheduleListNew) {
                if (!scheduleListOld.contains(scheduleListNewSchedule)) {
                    Room oldRoomIdOfScheduleListNewSchedule = scheduleListNewSchedule.getRoomId();
                    scheduleListNewSchedule.setRoomId(room);
                    scheduleListNewSchedule = em.merge(scheduleListNewSchedule);
                    if (oldRoomIdOfScheduleListNewSchedule != null && !oldRoomIdOfScheduleListNewSchedule.equals(room)) {
                        oldRoomIdOfScheduleListNewSchedule.getScheduleList().remove(scheduleListNewSchedule);
                        oldRoomIdOfScheduleListNewSchedule = em.merge(oldRoomIdOfScheduleListNewSchedule);
                    }
                }
            }
            for (Billboard billboardListNewBillboard : billboardListNew) {
                if (!billboardListOld.contains(billboardListNewBillboard)) {
                    Room oldRoomIdOfBillboardListNewBillboard = billboardListNewBillboard.getRoomId();
                    billboardListNewBillboard.setRoomId(room);
                    billboardListNewBillboard = em.merge(billboardListNewBillboard);
                    if (oldRoomIdOfBillboardListNewBillboard != null && !oldRoomIdOfBillboardListNewBillboard.equals(room)) {
                        oldRoomIdOfBillboardListNewBillboard.getBillboardList().remove(billboardListNewBillboard);
                        oldRoomIdOfBillboardListNewBillboard = em.merge(oldRoomIdOfBillboardListNewBillboard);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = room.getRoomId();
                if (findRoom(id) == null) {
                    throw new NonexistentEntityException("The room with id " + id + " no longer exists.");
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
            Room room;
            try {
                room = em.getReference(Room.class, id);
                room.getRoomId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The room with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Schedule> scheduleListOrphanCheck = room.getScheduleList();
            for (Schedule scheduleListOrphanCheckSchedule : scheduleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Room (" + room + ") cannot be destroyed since the Schedule " + scheduleListOrphanCheckSchedule + " in its scheduleList field has a non-nullable roomId field.");
            }
            List<Billboard> billboardListOrphanCheck = room.getBillboardList();
            for (Billboard billboardListOrphanCheckBillboard : billboardListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Room (" + room + ") cannot be destroyed since the Billboard " + billboardListOrphanCheckBillboard + " in its billboardList field has a non-nullable roomId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Room> findRoomEntities() {
        return findRoomEntities(true, -1, -1);
    }

    public List<Room> findRoomEntities(int maxResults, int firstResult) {
        return findRoomEntities(false, maxResults, firstResult);
    }

    private List<Room> findRoomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Room.class));
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

    public Room findRoom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Room.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Room> rt = cq.from(Room.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
