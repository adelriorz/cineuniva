/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "assistance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assistance.findAll", query = "SELECT a FROM Assistance a")
    , @NamedQuery(name = "Assistance.findByAssistanceId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.assistanceId = :assistanceId")
    , @NamedQuery(name = "Assistance.findByAssistanceStatus", query = "SELECT a FROM Assistance a WHERE a.assistanceStatus = :assistanceStatus")
    , @NamedQuery(name = "Assistance.findByAssistanceCreatedAt", query = "SELECT a FROM Assistance a WHERE a.assistanceCreatedAt = :assistanceCreatedAt")
    , @NamedQuery(name = "Assistance.findByAssistanceUpdatedAt", query = "SELECT a FROM Assistance a WHERE a.assistanceUpdatedAt = :assistanceUpdatedAt")
    , @NamedQuery(name = "Assistance.findByMoviemovieId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.moviemovieId = :moviemovieId")
    , @NamedQuery(name = "Assistance.findBySchedulescheduleId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.schedulescheduleId = :schedulescheduleId")
    , @NamedQuery(name = "Assistance.findByScheduleroomId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.scheduleroomId = :scheduleroomId")
    , @NamedQuery(name = "Assistance.findByMunicipalitymunicipalityId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.municipalitymunicipalityId = :municipalitymunicipalityId")
    , @NamedQuery(name = "Assistance.findByMunicipalitystateId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.municipalitystateId = :municipalitystateId")
    , @NamedQuery(name = "Assistance.findByUseruserId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.useruserId = :useruserId")
    , @NamedQuery(name = "Assistance.findByRoomroomId", query = "SELECT a FROM Assistance a WHERE a.assistancePK.roomroomId = :roomroomId")})
public class Assistance implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AssistancePK assistancePK;
    @Basic(optional = false)
    @Column(name = "assistanceStatus")
    private boolean assistanceStatus;
    @Basic(optional = false)
    @Column(name = "assistanceCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assistanceCreatedAt;
    @Basic(optional = false)
    @Column(name = "assistanceUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assistanceUpdatedAt;
    @JoinColumn(name = "movie_movieId", referencedColumnName = "movieId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movie movie;
    @JoinColumns({
        @JoinColumn(name = "municipality_municipalityId", referencedColumnName = "municipalityId", insertable = false, updatable = false)
        , @JoinColumn(name = "municipality_stateId", referencedColumnName = "stateId", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Municipality municipality;
    @JoinColumn(name = "room_roomId", referencedColumnName = "roomId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Room room;
    @JoinColumns({
        @JoinColumn(name = "schedule_scheduleId", referencedColumnName = "scheduleId", insertable = false, updatable = false)
        , @JoinColumn(name = "schedule_roomId", referencedColumnName = "roomId", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Schedule schedule;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Assistance() {
    }

    public Assistance(AssistancePK assistancePK) {
        this.assistancePK = assistancePK;
    }

    public Assistance(AssistancePK assistancePK, boolean assistanceStatus, Date assistanceCreatedAt, Date assistanceUpdatedAt) {
        this.assistancePK = assistancePK;
        this.assistanceStatus = assistanceStatus;
        this.assistanceCreatedAt = assistanceCreatedAt;
        this.assistanceUpdatedAt = assistanceUpdatedAt;
    }

    public Assistance(int assistanceId, int moviemovieId, int schedulescheduleId, int scheduleroomId, int municipalitymunicipalityId, int municipalitystateId, int useruserId, int roomroomId) {
        this.assistancePK = new AssistancePK(assistanceId, moviemovieId, schedulescheduleId, scheduleroomId, municipalitymunicipalityId, municipalitystateId, useruserId, roomroomId);
    }

    public AssistancePK getAssistancePK() {
        return assistancePK;
    }

    public void setAssistancePK(AssistancePK assistancePK) {
        this.assistancePK = assistancePK;
    }

    public boolean getAssistanceStatus() {
        return assistanceStatus;
    }

    public void setAssistanceStatus(boolean assistanceStatus) {
        this.assistanceStatus = assistanceStatus;
    }

    public Date getAssistanceCreatedAt() {
        return assistanceCreatedAt;
    }

    public void setAssistanceCreatedAt(Date assistanceCreatedAt) {
        this.assistanceCreatedAt = assistanceCreatedAt;
    }

    public Date getAssistanceUpdatedAt() {
        return assistanceUpdatedAt;
    }

    public void setAssistanceUpdatedAt(Date assistanceUpdatedAt) {
        this.assistanceUpdatedAt = assistanceUpdatedAt;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assistancePK != null ? assistancePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assistance)) {
            return false;
        }
        Assistance other = (Assistance) object;
        if ((this.assistancePK == null && other.assistancePK != null) || (this.assistancePK != null && !this.assistancePK.equals(other.assistancePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Assistance[ assistancePK=" + assistancePK + " ]";
    }
    
}
