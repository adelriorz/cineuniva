/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Armando Del Rio
 */
@Embeddable
public class AssistancePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "assistanceId")
    private int assistanceId;
    @Basic(optional = false)
    @Column(name = "movie_movieId")
    private int moviemovieId;
    @Basic(optional = false)
    @Column(name = "schedule_scheduleId")
    private int schedulescheduleId;
    @Basic(optional = false)
    @Column(name = "schedule_roomId")
    private int scheduleroomId;
    @Basic(optional = false)
    @Column(name = "municipality_municipalityId")
    private int municipalitymunicipalityId;
    @Basic(optional = false)
    @Column(name = "municipality_stateId")
    private int municipalitystateId;
    @Basic(optional = false)
    @Column(name = "user_userId")
    private int useruserId;
    @Basic(optional = false)
    @Column(name = "room_roomId")
    private int roomroomId;

    public AssistancePK() {
    }

    public AssistancePK(int assistanceId, int moviemovieId, int schedulescheduleId, int scheduleroomId, int municipalitymunicipalityId, int municipalitystateId, int useruserId, int roomroomId) {
        this.assistanceId = assistanceId;
        this.moviemovieId = moviemovieId;
        this.schedulescheduleId = schedulescheduleId;
        this.scheduleroomId = scheduleroomId;
        this.municipalitymunicipalityId = municipalitymunicipalityId;
        this.municipalitystateId = municipalitystateId;
        this.useruserId = useruserId;
        this.roomroomId = roomroomId;
    }

    public int getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(int assistanceId) {
        this.assistanceId = assistanceId;
    }

    public int getMoviemovieId() {
        return moviemovieId;
    }

    public void setMoviemovieId(int moviemovieId) {
        this.moviemovieId = moviemovieId;
    }

    public int getSchedulescheduleId() {
        return schedulescheduleId;
    }

    public void setSchedulescheduleId(int schedulescheduleId) {
        this.schedulescheduleId = schedulescheduleId;
    }

    public int getScheduleroomId() {
        return scheduleroomId;
    }

    public void setScheduleroomId(int scheduleroomId) {
        this.scheduleroomId = scheduleroomId;
    }

    public int getMunicipalitymunicipalityId() {
        return municipalitymunicipalityId;
    }

    public void setMunicipalitymunicipalityId(int municipalitymunicipalityId) {
        this.municipalitymunicipalityId = municipalitymunicipalityId;
    }

    public int getMunicipalitystateId() {
        return municipalitystateId;
    }

    public void setMunicipalitystateId(int municipalitystateId) {
        this.municipalitystateId = municipalitystateId;
    }

    public int getUseruserId() {
        return useruserId;
    }

    public void setUseruserId(int useruserId) {
        this.useruserId = useruserId;
    }

    public int getRoomroomId() {
        return roomroomId;
    }

    public void setRoomroomId(int roomroomId) {
        this.roomroomId = roomroomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) assistanceId;
        hash += (int) moviemovieId;
        hash += (int) schedulescheduleId;
        hash += (int) scheduleroomId;
        hash += (int) municipalitymunicipalityId;
        hash += (int) municipalitystateId;
        hash += (int) useruserId;
        hash += (int) roomroomId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssistancePK)) {
            return false;
        }
        AssistancePK other = (AssistancePK) object;
        if (this.assistanceId != other.assistanceId) {
            return false;
        }
        if (this.moviemovieId != other.moviemovieId) {
            return false;
        }
        if (this.schedulescheduleId != other.schedulescheduleId) {
            return false;
        }
        if (this.scheduleroomId != other.scheduleroomId) {
            return false;
        }
        if (this.municipalitymunicipalityId != other.municipalitymunicipalityId) {
            return false;
        }
        if (this.municipalitystateId != other.municipalitystateId) {
            return false;
        }
        if (this.useruserId != other.useruserId) {
            return false;
        }
        if (this.roomroomId != other.roomroomId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AssistancePK[ assistanceId=" + assistanceId + ", moviemovieId=" + moviemovieId + ", schedulescheduleId=" + schedulescheduleId + ", scheduleroomId=" + scheduleroomId + ", municipalitymunicipalityId=" + municipalitymunicipalityId + ", municipalitystateId=" + municipalitystateId + ", useruserId=" + useruserId + ", roomroomId=" + roomroomId + " ]";
    }
    
}
