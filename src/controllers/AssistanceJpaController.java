/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entities.Assistance;
import entities.AssistancePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Movie;
import entities.Municipality;
import entities.Room;
import entities.Schedule;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Armando Del Rio
 */
public class AssistanceJpaController implements Serializable {

    public AssistanceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Assistance assistance) throws PreexistingEntityException, Exception {
        if (assistance.getAssistancePK() == null) {
            assistance.setAssistancePK(new AssistancePK());
        }
        assistance.getAssistancePK().setMoviemovieId(assistance.getMovie().getMovieId());
        assistance.getAssistancePK().setScheduleroomId(assistance.getSchedule().getSchedulePK().getRoomId());
        assistance.getAssistancePK().setSchedulescheduleId(assistance.getSchedule().getSchedulePK().getScheduleId());
        assistance.getAssistancePK().setMunicipalitystateId(assistance.getMunicipality().getMunicipalityPK().getStateId());
        assistance.getAssistancePK().setMunicipalitymunicipalityId(assistance.getMunicipality().getMunicipalityPK().getMunicipalityId());
        assistance.getAssistancePK().setRoomroomId(assistance.getRoom().getRoomId());
        assistance.getAssistancePK().setUseruserId(assistance.getUser().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movie = assistance.getMovie();
            if (movie != null) {
                movie = em.getReference(movie.getClass(), movie.getMovieId());
                assistance.setMovie(movie);
            }
            Municipality municipality = assistance.getMunicipality();
            if (municipality != null) {
                municipality = em.getReference(municipality.getClass(), municipality.getMunicipalityPK());
                assistance.setMunicipality(municipality);
            }
            Room room = assistance.getRoom();
            if (room != null) {
                room = em.getReference(room.getClass(), room.getRoomId());
                assistance.setRoom(room);
            }
            Schedule schedule = assistance.getSchedule();
            if (schedule != null) {
                schedule = em.getReference(schedule.getClass(), schedule.getSchedulePK());
                assistance.setSchedule(schedule);
            }
            User user = assistance.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getUserId());
                assistance.setUser(user);
            }
            em.persist(assistance);
            if (movie != null) {
                movie.getAssistanceList().add(assistance);
                movie = em.merge(movie);
            }
            if (municipality != null) {
                municipality.getAssistanceList().add(assistance);
                municipality = em.merge(municipality);
            }
            if (room != null) {
                room.getAssistanceList().add(assistance);
                room = em.merge(room);
            }
            if (schedule != null) {
                schedule.getAssistanceList().add(assistance);
                schedule = em.merge(schedule);
            }
            if (user != null) {
                user.getAssistanceList().add(assistance);
                user = em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAssistance(assistance.getAssistancePK()) != null) {
                throw new PreexistingEntityException("Assistance " + assistance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Assistance assistance) throws NonexistentEntityException, Exception {
        assistance.getAssistancePK().setMoviemovieId(assistance.getMovie().getMovieId());
        assistance.getAssistancePK().setScheduleroomId(assistance.getSchedule().getSchedulePK().getRoomId());
        assistance.getAssistancePK().setSchedulescheduleId(assistance.getSchedule().getSchedulePK().getScheduleId());
        assistance.getAssistancePK().setMunicipalitystateId(assistance.getMunicipality().getMunicipalityPK().getStateId());
        assistance.getAssistancePK().setMunicipalitymunicipalityId(assistance.getMunicipality().getMunicipalityPK().getMunicipalityId());
        assistance.getAssistancePK().setRoomroomId(assistance.getRoom().getRoomId());
        assistance.getAssistancePK().setUseruserId(assistance.getUser().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assistance persistentAssistance = em.find(Assistance.class, assistance.getAssistancePK());
            Movie movieOld = persistentAssistance.getMovie();
            Movie movieNew = assistance.getMovie();
            Municipality municipalityOld = persistentAssistance.getMunicipality();
            Municipality municipalityNew = assistance.getMunicipality();
            Room roomOld = persistentAssistance.getRoom();
            Room roomNew = assistance.getRoom();
            Schedule scheduleOld = persistentAssistance.getSchedule();
            Schedule scheduleNew = assistance.getSchedule();
            User userOld = persistentAssistance.getUser();
            User userNew = assistance.getUser();
            if (movieNew != null) {
                movieNew = em.getReference(movieNew.getClass(), movieNew.getMovieId());
                assistance.setMovie(movieNew);
            }
            if (municipalityNew != null) {
                municipalityNew = em.getReference(municipalityNew.getClass(), municipalityNew.getMunicipalityPK());
                assistance.setMunicipality(municipalityNew);
            }
            if (roomNew != null) {
                roomNew = em.getReference(roomNew.getClass(), roomNew.getRoomId());
                assistance.setRoom(roomNew);
            }
            if (scheduleNew != null) {
                scheduleNew = em.getReference(scheduleNew.getClass(), scheduleNew.getSchedulePK());
                assistance.setSchedule(scheduleNew);
            }
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getUserId());
                assistance.setUser(userNew);
            }
            assistance = em.merge(assistance);
            if (movieOld != null && !movieOld.equals(movieNew)) {
                movieOld.getAssistanceList().remove(assistance);
                movieOld = em.merge(movieOld);
            }
            if (movieNew != null && !movieNew.equals(movieOld)) {
                movieNew.getAssistanceList().add(assistance);
                movieNew = em.merge(movieNew);
            }
            if (municipalityOld != null && !municipalityOld.equals(municipalityNew)) {
                municipalityOld.getAssistanceList().remove(assistance);
                municipalityOld = em.merge(municipalityOld);
            }
            if (municipalityNew != null && !municipalityNew.equals(municipalityOld)) {
                municipalityNew.getAssistanceList().add(assistance);
                municipalityNew = em.merge(municipalityNew);
            }
            if (roomOld != null && !roomOld.equals(roomNew)) {
                roomOld.getAssistanceList().remove(assistance);
                roomOld = em.merge(roomOld);
            }
            if (roomNew != null && !roomNew.equals(roomOld)) {
                roomNew.getAssistanceList().add(assistance);
                roomNew = em.merge(roomNew);
            }
            if (scheduleOld != null && !scheduleOld.equals(scheduleNew)) {
                scheduleOld.getAssistanceList().remove(assistance);
                scheduleOld = em.merge(scheduleOld);
            }
            if (scheduleNew != null && !scheduleNew.equals(scheduleOld)) {
                scheduleNew.getAssistanceList().add(assistance);
                scheduleNew = em.merge(scheduleNew);
            }
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getAssistanceList().remove(assistance);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getAssistanceList().add(assistance);
                userNew = em.merge(userNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AssistancePK id = assistance.getAssistancePK();
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

    public void destroy(AssistancePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assistance assistance;
            try {
                assistance = em.getReference(Assistance.class, id);
                assistance.getAssistancePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assistance with id " + id + " no longer exists.", enfe);
            }
            Movie movie = assistance.getMovie();
            if (movie != null) {
                movie.getAssistanceList().remove(assistance);
                movie = em.merge(movie);
            }
            Municipality municipality = assistance.getMunicipality();
            if (municipality != null) {
                municipality.getAssistanceList().remove(assistance);
                municipality = em.merge(municipality);
            }
            Room room = assistance.getRoom();
            if (room != null) {
                room.getAssistanceList().remove(assistance);
                room = em.merge(room);
            }
            Schedule schedule = assistance.getSchedule();
            if (schedule != null) {
                schedule.getAssistanceList().remove(assistance);
                schedule = em.merge(schedule);
            }
            User user = assistance.getUser();
            if (user != null) {
                user.getAssistanceList().remove(assistance);
                user = em.merge(user);
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

    public Assistance findAssistance(AssistancePK id) {
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
