/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
    , @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.scheduleId = :scheduleId")
    , @NamedQuery(name = "Schedule.findByScheduleStart", query = "SELECT s FROM Schedule s WHERE s.scheduleStart = :scheduleStart")
    , @NamedQuery(name = "Schedule.findByScheduleEnd", query = "SELECT s FROM Schedule s WHERE s.scheduleEnd = :scheduleEnd")
    , @NamedQuery(name = "Schedule.findByScheduleStatus", query = "SELECT s FROM Schedule s WHERE s.scheduleStatus = :scheduleStatus")
    , @NamedQuery(name = "Schedule.findByScheduleCreatedAt", query = "SELECT s FROM Schedule s WHERE s.scheduleCreatedAt = :scheduleCreatedAt")
    , @NamedQuery(name = "Schedule.findByScheduleUpdatedAt", query = "SELECT s FROM Schedule s WHERE s.scheduleUpdatedAt = :scheduleUpdatedAt")
    , @NamedQuery(name = "Schedule.findByRoomId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.roomId = :roomId")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SchedulePK schedulePK;
    @Basic(optional = false)
    @Column(name = "scheduleStart")
    private String scheduleStart;
    @Basic(optional = false)
    @Column(name = "scheduleEnd")
    private String scheduleEnd;
    @Basic(optional = false)
    @Column(name = "scheduleStatus")
    private boolean scheduleStatus;
    @Basic(optional = false)
    @Column(name = "scheduleCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleCreatedAt;
    @Basic(optional = false)
    @Column(name = "scheduleUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleUpdatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private List<Assistance> assistanceList;

    public Schedule() {
    }

    public Schedule(SchedulePK schedulePK) {
        this.schedulePK = schedulePK;
    }

    public Schedule(SchedulePK schedulePK, String scheduleStart, String scheduleEnd, boolean scheduleStatus, Date scheduleCreatedAt, Date scheduleUpdatedAt) {
        this.schedulePK = schedulePK;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.scheduleStatus = scheduleStatus;
        this.scheduleCreatedAt = scheduleCreatedAt;
        this.scheduleUpdatedAt = scheduleUpdatedAt;
    }

    public Schedule(int scheduleId, int roomId) {
        this.schedulePK = new SchedulePK(scheduleId, roomId);
    }

    public SchedulePK getSchedulePK() {
        return schedulePK;
    }

    public void setSchedulePK(SchedulePK schedulePK) {
        this.schedulePK = schedulePK;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(String scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public boolean getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(boolean scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Date getScheduleCreatedAt() {
        return scheduleCreatedAt;
    }

    public void setScheduleCreatedAt(Date scheduleCreatedAt) {
        this.scheduleCreatedAt = scheduleCreatedAt;
    }

    public Date getScheduleUpdatedAt() {
        return scheduleUpdatedAt;
    }

    public void setScheduleUpdatedAt(Date scheduleUpdatedAt) {
        this.scheduleUpdatedAt = scheduleUpdatedAt;
    }

    @XmlTransient
    public List<Assistance> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(List<Assistance> assistanceList) {
        this.assistanceList = assistanceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schedulePK != null ? schedulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.schedulePK == null && other.schedulePK != null) || (this.schedulePK != null && !this.schedulePK.equals(other.schedulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Schedule[ schedulePK=" + schedulePK + " ]";
    }
    
}
