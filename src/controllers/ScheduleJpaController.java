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
import entities.Room;
import entities.MovieSchedule;
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
        if (schedule.getMovieScheduleList() == null) {
            schedule.setMovieScheduleList(new ArrayList<MovieSchedule>());
        }
        schedule.getSchedulePK().setRoomId(schedule.getRoom().getRoomId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room room = schedule.getRoom();
            if (room != null) {
                room = em.getReference(room.getClass(), room.getRoomId());
                schedule.setRoom(room);
            }
            List<MovieSchedule> attachedMovieScheduleList = new ArrayList<MovieSchedule>();
            for (MovieSchedule movieScheduleListMovieScheduleToAttach : schedule.getMovieScheduleList()) {
                movieScheduleListMovieScheduleToAttach = em.getReference(movieScheduleListMovieScheduleToAttach.getClass(), movieScheduleListMovieScheduleToAttach.getMovieSchedulePK());
                attachedMovieScheduleList.add(movieScheduleListMovieScheduleToAttach);
            }
            schedule.setMovieScheduleList(attachedMovieScheduleList);
            em.persist(schedule);
            if (room != null) {
                room.getScheduleList().add(schedule);
                room = em.merge(room);
            }
            for (MovieSchedule movieScheduleListMovieSchedule : schedule.getMovieScheduleList()) {
                Schedule oldScheduleOfMovieScheduleListMovieSchedule = movieScheduleListMovieSchedule.getSchedule();
                movieScheduleListMovieSchedule.setSchedule(schedule);
                movieScheduleListMovieSchedule = em.merge(movieScheduleListMovieSchedule);
                if (oldScheduleOfMovieScheduleListMovieSchedule != null) {
                    oldScheduleOfMovieScheduleListMovieSchedule.getMovieScheduleList().remove(movieScheduleListMovieSchedule);
                    oldScheduleOfMovieScheduleListMovieSchedule = em.merge(oldScheduleOfMovieScheduleListMovieSchedule);
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
        schedule.getSchedulePK().setRoomId(schedule.getRoom().getRoomId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schedule persistentSchedule = em.find(Schedule.class, schedule.getSchedulePK());
            Room roomOld = persistentSchedule.getRoom();
            Room roomNew = schedule.getRoom();
            List<MovieSchedule> movieScheduleListOld = persistentSchedule.getMovieScheduleList();
            List<MovieSchedule> movieScheduleListNew = schedule.getMovieScheduleList();
            List<String> illegalOrphanMessages = null;
            for (MovieSchedule movieScheduleListOldMovieSchedule : movieScheduleListOld) {
                if (!movieScheduleListNew.contains(movieScheduleListOldMovieSchedule)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovieSchedule " + movieScheduleListOldMovieSchedule + " since its schedule field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roomNew != null) {
                roomNew = em.getReference(roomNew.getClass(), roomNew.getRoomId());
                schedule.setRoom(roomNew);
            }
            List<MovieSchedule> attachedMovieScheduleListNew = new ArrayList<MovieSchedule>();
            for (MovieSchedule movieScheduleListNewMovieScheduleToAttach : movieScheduleListNew) {
                movieScheduleListNewMovieScheduleToAttach = em.getReference(movieScheduleListNewMovieScheduleToAttach.getClass(), movieScheduleListNewMovieScheduleToAttach.getMovieSchedulePK());
                attachedMovieScheduleListNew.add(movieScheduleListNewMovieScheduleToAttach);
            }
            movieScheduleListNew = attachedMovieScheduleListNew;
            schedule.setMovieScheduleList(movieScheduleListNew);
            schedule = em.merge(schedule);
            if (roomOld != null && !roomOld.equals(roomNew)) {
                roomOld.getScheduleList().remove(schedule);
                roomOld = em.merge(roomOld);
            }
            if (roomNew != null && !roomNew.equals(roomOld)) {
                roomNew.getScheduleList().add(schedule);
                roomNew = em.merge(roomNew);
            }
            for (MovieSchedule movieScheduleListNewMovieSchedule : movieScheduleListNew) {
                if (!movieScheduleListOld.contains(movieScheduleListNewMovieSchedule)) {
                    Schedule oldScheduleOfMovieScheduleListNewMovieSchedule = movieScheduleListNewMovieSchedule.getSchedule();
                    movieScheduleListNewMovieSchedule.setSchedule(schedule);
                    movieScheduleListNewMovieSchedule = em.merge(movieScheduleListNewMovieSchedule);
                    if (oldScheduleOfMovieScheduleListNewMovieSchedule != null && !oldScheduleOfMovieScheduleListNewMovieSchedule.equals(schedule)) {
                        oldScheduleOfMovieScheduleListNewMovieSchedule.getMovieScheduleList().remove(movieScheduleListNewMovieSchedule);
                        oldScheduleOfMovieScheduleListNewMovieSchedule = em.merge(oldScheduleOfMovieScheduleListNewMovieSchedule);
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
            List<MovieSchedule> movieScheduleListOrphanCheck = schedule.getMovieScheduleList();
            for (MovieSchedule movieScheduleListOrphanCheckMovieSchedule : movieScheduleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Schedule (" + schedule + ") cannot be destroyed since the MovieSchedule " + movieScheduleListOrphanCheckMovieSchedule + " in its movieScheduleList field has a non-nullable schedule field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Room room = schedule.getRoom();
            if (room != null) {
                room.getScheduleList().remove(schedule);
                room = em.merge(room);
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
