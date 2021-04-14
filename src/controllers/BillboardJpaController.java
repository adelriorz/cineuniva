package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Movie;
import entities.Room;
import entities.Schedule;
import entities.State;
import entities.Assistance;
import entities.Billboard;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
**Written by: Armando Del Río Ramírez & Paola Escalera
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code that allows CRUD operations for Billboard Entity 
*/
public class BillboardJpaController implements Serializable {

    public BillboardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public BillboardJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Billboard billboard) {
        if (billboard.getAssistanceList() == null) {
            billboard.setAssistanceList(new ArrayList<Assistance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieId = billboard.getMovieId();
            if (movieId != null) {
                movieId = em.getReference(movieId.getClass(), movieId.getMovieId());
                billboard.setMovieId(movieId);
            }
            Room roomId = billboard.getRoomId();
            if (roomId != null) {
                roomId = em.getReference(roomId.getClass(), roomId.getRoomId());
                billboard.setRoomId(roomId);
            }
            Schedule scheduleId = billboard.getScheduleId();
            if (scheduleId != null) {
                scheduleId = em.getReference(scheduleId.getClass(), scheduleId.getScheduleId());
                billboard.setScheduleId(scheduleId);
            }
            State stateId = billboard.getStateId();
            if (stateId != null) {
                stateId = em.getReference(stateId.getClass(), stateId.getStateId());
                billboard.setStateId(stateId);
            }
            List<Assistance> attachedAssistanceList = new ArrayList<Assistance>();
            for (Assistance assistanceListAssistanceToAttach : billboard.getAssistanceList()) {
                assistanceListAssistanceToAttach = em.getReference(assistanceListAssistanceToAttach.getClass(), assistanceListAssistanceToAttach.getAssistanceId());
                attachedAssistanceList.add(assistanceListAssistanceToAttach);
            }
            billboard.setAssistanceList(attachedAssistanceList);
            em.persist(billboard);
            if (movieId != null) {
                movieId.getBillboardList().add(billboard);
                movieId = em.merge(movieId);
            }
            if (roomId != null) {
                roomId.getBillboardList().add(billboard);
                roomId = em.merge(roomId);
            }
            if (scheduleId != null) {
                scheduleId.getBillboardList().add(billboard);
                scheduleId = em.merge(scheduleId);
            }
            if (stateId != null) {
                stateId.getBillboardList().add(billboard);
                stateId = em.merge(stateId);
            }
            for (Assistance assistanceListAssistance : billboard.getAssistanceList()) {
                Billboard oldBillboardIdOfAssistanceListAssistance = assistanceListAssistance.getBillboardId();
                assistanceListAssistance.setBillboardId(billboard);
                assistanceListAssistance = em.merge(assistanceListAssistance);
                if (oldBillboardIdOfAssistanceListAssistance != null) {
                    oldBillboardIdOfAssistanceListAssistance.getAssistanceList().remove(assistanceListAssistance);
                    oldBillboardIdOfAssistanceListAssistance = em.merge(oldBillboardIdOfAssistanceListAssistance);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Billboard billboard) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Billboard persistentBillboard = em.find(Billboard.class, billboard.getBillboardId());
            Movie movieIdOld = persistentBillboard.getMovieId();
            Movie movieIdNew = billboard.getMovieId();
            Room roomIdOld = persistentBillboard.getRoomId();
            Room roomIdNew = billboard.getRoomId();
            Schedule scheduleIdOld = persistentBillboard.getScheduleId();
            Schedule scheduleIdNew = billboard.getScheduleId();
            State stateIdOld = persistentBillboard.getStateId();
            State stateIdNew = billboard.getStateId();
            List<Assistance> assistanceListOld = persistentBillboard.getAssistanceList();
            List<Assistance> assistanceListNew = billboard.getAssistanceList();
            List<String> illegalOrphanMessages = null;
            for (Assistance assistanceListOldAssistance : assistanceListOld) {
                if (!assistanceListNew.contains(assistanceListOldAssistance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assistance " + assistanceListOldAssistance + " since its billboardId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movieIdNew != null) {
                movieIdNew = em.getReference(movieIdNew.getClass(), movieIdNew.getMovieId());
                billboard.setMovieId(movieIdNew);
            }
            if (roomIdNew != null) {
                roomIdNew = em.getReference(roomIdNew.getClass(), roomIdNew.getRoomId());
                billboard.setRoomId(roomIdNew);
            }
            if (scheduleIdNew != null) {
                scheduleIdNew = em.getReference(scheduleIdNew.getClass(), scheduleIdNew.getScheduleId());
                billboard.setScheduleId(scheduleIdNew);
            }
            if (stateIdNew != null) {
                stateIdNew = em.getReference(stateIdNew.getClass(), stateIdNew.getStateId());
                billboard.setStateId(stateIdNew);
            }
            List<Assistance> attachedAssistanceListNew = new ArrayList<Assistance>();
            for (Assistance assistanceListNewAssistanceToAttach : assistanceListNew) {
                assistanceListNewAssistanceToAttach = em.getReference(assistanceListNewAssistanceToAttach.getClass(), assistanceListNewAssistanceToAttach.getAssistanceId());
                attachedAssistanceListNew.add(assistanceListNewAssistanceToAttach);
            }
            assistanceListNew = attachedAssistanceListNew;
            billboard.setAssistanceList(assistanceListNew);
            billboard = em.merge(billboard);
            if (movieIdOld != null && !movieIdOld.equals(movieIdNew)) {
                movieIdOld.getBillboardList().remove(billboard);
                movieIdOld = em.merge(movieIdOld);
            }
            if (movieIdNew != null && !movieIdNew.equals(movieIdOld)) {
                movieIdNew.getBillboardList().add(billboard);
                movieIdNew = em.merge(movieIdNew);
            }
            if (roomIdOld != null && !roomIdOld.equals(roomIdNew)) {
                roomIdOld.getBillboardList().remove(billboard);
                roomIdOld = em.merge(roomIdOld);
            }
            if (roomIdNew != null && !roomIdNew.equals(roomIdOld)) {
                roomIdNew.getBillboardList().add(billboard);
                roomIdNew = em.merge(roomIdNew);
            }
            if (scheduleIdOld != null && !scheduleIdOld.equals(scheduleIdNew)) {
                scheduleIdOld.getBillboardList().remove(billboard);
                scheduleIdOld = em.merge(scheduleIdOld);
            }
            if (scheduleIdNew != null && !scheduleIdNew.equals(scheduleIdOld)) {
                scheduleIdNew.getBillboardList().add(billboard);
                scheduleIdNew = em.merge(scheduleIdNew);
            }
            if (stateIdOld != null && !stateIdOld.equals(stateIdNew)) {
                stateIdOld.getBillboardList().remove(billboard);
                stateIdOld = em.merge(stateIdOld);
            }
            if (stateIdNew != null && !stateIdNew.equals(stateIdOld)) {
                stateIdNew.getBillboardList().add(billboard);
                stateIdNew = em.merge(stateIdNew);
            }
            for (Assistance assistanceListNewAssistance : assistanceListNew) {
                if (!assistanceListOld.contains(assistanceListNewAssistance)) {
                    Billboard oldBillboardIdOfAssistanceListNewAssistance = assistanceListNewAssistance.getBillboardId();
                    assistanceListNewAssistance.setBillboardId(billboard);
                    assistanceListNewAssistance = em.merge(assistanceListNewAssistance);
                    if (oldBillboardIdOfAssistanceListNewAssistance != null && !oldBillboardIdOfAssistanceListNewAssistance.equals(billboard)) {
                        oldBillboardIdOfAssistanceListNewAssistance.getAssistanceList().remove(assistanceListNewAssistance);
                        oldBillboardIdOfAssistanceListNewAssistance = em.merge(oldBillboardIdOfAssistanceListNewAssistance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = billboard.getBillboardId();
                if (findBillboard(id) == null) {
                    throw new NonexistentEntityException("The billboard with id " + id + " no longer exists.");
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
            Billboard billboard;
            try {
                billboard = em.getReference(Billboard.class, id);
                billboard.getBillboardId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The billboard with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assistance> assistanceListOrphanCheck = billboard.getAssistanceList();
            for (Assistance assistanceListOrphanCheckAssistance : assistanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Billboard (" + billboard + ") cannot be destroyed since the Assistance " + assistanceListOrphanCheckAssistance + " in its assistanceList field has a non-nullable billboardId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Movie movieId = billboard.getMovieId();
            if (movieId != null) {
                movieId.getBillboardList().remove(billboard);
                movieId = em.merge(movieId);
            }
            Room roomId = billboard.getRoomId();
            if (roomId != null) {
                roomId.getBillboardList().remove(billboard);
                roomId = em.merge(roomId);
            }
            Schedule scheduleId = billboard.getScheduleId();
            if (scheduleId != null) {
                scheduleId.getBillboardList().remove(billboard);
                scheduleId = em.merge(scheduleId);
            }
            State stateId = billboard.getStateId();
            if (stateId != null) {
                stateId.getBillboardList().remove(billboard);
                stateId = em.merge(stateId);
            }
            em.remove(billboard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Billboard> findBillboardEntities() {
        return findBillboardEntities(true, -1, -1);
    }

    public List<Billboard> findBillboardEntities(int maxResults, int firstResult) {
        return findBillboardEntities(false, maxResults, firstResult);
    }

    private List<Billboard> findBillboardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Billboard.class));
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

    public Billboard findBillboard(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Billboard.class, id);
        } finally {
            em.close();
        }
    }

    public int getBillboardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Billboard> rt = cq.from(Billboard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
