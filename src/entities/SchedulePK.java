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
public class SchedulePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "scheduleId")
    private int scheduleId;
    @Basic(optional = false)
    @Column(name = "roomId")
    private int roomId;

    public SchedulePK() {
    }

    public SchedulePK(int scheduleId, int roomId) {
        this.scheduleId = scheduleId;
        this.roomId = roomId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) scheduleId;
        hash += (int) roomId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchedulePK)) {
            return false;
        }
        SchedulePK other = (SchedulePK) object;
        if (this.scheduleId != other.scheduleId) {
            return false;
        }
        if (this.roomId != other.roomId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SchedulePK[ scheduleId=" + scheduleId + ", roomId=" + roomId + " ]";
    }
    
}
