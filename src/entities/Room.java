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
**Description: Code to create new Room Entity
*/
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
    , @NamedQuery(name = "Room.findByRoomId", query = "SELECT r FROM Room r WHERE r.roomId = :roomId")
    , @NamedQuery(name = "Room.findByRoomNumber", query = "SELECT r FROM Room r WHERE r.roomNumber = :roomNumber")
    , @NamedQuery(name = "Room.findByRoomStatus", query = "SELECT r FROM Room r WHERE r.roomStatus = :roomStatus")
    , @NamedQuery(name = "Room.findByRoomCreatedAt", query = "SELECT r FROM Room r WHERE r.roomCreatedAt = :roomCreatedAt")
    , @NamedQuery(name = "Room.findByRoomUpdatedAt", query = "SELECT r FROM Room r WHERE r.roomUpdatedAt = :roomUpdatedAt")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomId")
    private Integer roomId;
    @Basic(optional = false)
    @Column(name = "roomNumber")
    private int roomNumber;
    @Basic(optional = false)
    @Column(name = "roomStatus")
    private boolean roomStatus;
    @Basic(optional = false)
    @Column(name = "roomCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roomCreatedAt;
    @Basic(optional = false)
    @Column(name = "roomUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roomUpdatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Schedule> scheduleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Billboard> billboardList;

    public Room() {
    }

    public Room(Integer roomId) {
        this.roomId = roomId;
    }

    public Room(Integer roomId, int roomNumber, boolean roomStatus, Date roomCreatedAt, Date roomUpdatedAt) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
        this.roomCreatedAt = roomCreatedAt;
        this.roomUpdatedAt = roomUpdatedAt;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Date getRoomCreatedAt() {
        return roomCreatedAt;
    }

    public void setRoomCreatedAt(Date roomCreatedAt) {
        this.roomCreatedAt = roomCreatedAt;
    }

    public Date getRoomUpdatedAt() {
        return roomUpdatedAt;
    }

    public void setRoomUpdatedAt(Date roomUpdatedAt) {
        this.roomUpdatedAt = roomUpdatedAt;
    }

    @XmlTransient
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
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
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Room[ roomId=" + roomId + " ]";
    }
    
}
