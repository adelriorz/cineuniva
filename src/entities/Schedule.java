package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code for CRUD Schedule operations
*/
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
    , @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId")
    , @NamedQuery(name = "Schedule.findByScheduleStart", query = "SELECT s FROM Schedule s WHERE s.scheduleStart = :scheduleStart")
    , @NamedQuery(name = "Schedule.findByScheduleEnd", query = "SELECT s FROM Schedule s WHERE s.scheduleEnd = :scheduleEnd")
    , @NamedQuery(name = "Schedule.findByScheduleStatus", query = "SELECT s FROM Schedule s WHERE s.scheduleStatus = :scheduleStatus")
    , @NamedQuery(name = "Schedule.findByScheduleCreatedAt", query = "SELECT s FROM Schedule s WHERE s.scheduleCreatedAt = :scheduleCreatedAt")
    , @NamedQuery(name = "Schedule.findByScheduleUpdatedAt", query = "SELECT s FROM Schedule s WHERE s.scheduleUpdatedAt = :scheduleUpdatedAt")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleId")
    private Integer scheduleId;
    @Basic(optional = false)
    @Column(name = "scheduleStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleStart;
    @Basic(optional = false)
    @Column(name = "scheduleEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleEnd;
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
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    @ManyToOne(optional = false)
    private Room roomId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scheduleId")
    private List<Billboard> billboardList;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule(Integer scheduleId, Date scheduleStart, Date scheduleEnd, boolean scheduleStatus, Date scheduleCreatedAt, Date scheduleUpdatedAt) {
        this.scheduleId = scheduleId;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.scheduleStatus = scheduleStatus;
        this.scheduleCreatedAt = scheduleCreatedAt;
        this.scheduleUpdatedAt = scheduleUpdatedAt;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(Date scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public Date getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(Date scheduleEnd) {
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

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @XmlTransient
    public List<Billboard> getBillboardList() {
        return billboardList;
    }

    public void setBillboardList(List<Billboard> billboardList) {
        this.billboardList = billboardList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Schedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
