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

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "billboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billboard.findAll", query = "SELECT b FROM Billboard b")
    , @NamedQuery(name = "Billboard.findByBillboardId", query = "SELECT b FROM Billboard b WHERE b.billboardId = :billboardId")
    , @NamedQuery(name = "Billboard.findByBillboardCreatedAt", query = "SELECT b FROM Billboard b WHERE b.billboardCreatedAt = :billboardCreatedAt")
    , @NamedQuery(name = "Billboard.findByBillboardUpdatedAt", query = "SELECT b FROM Billboard b WHERE b.billboardUpdatedAt = :billboardUpdatedAt")
    , @NamedQuery(name = "Billboard.findByBillboardStatus", query = "SELECT b FROM Billboard b WHERE b.billboardStatus = :billboardStatus")})
public class Billboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "billboardId")
    private Integer billboardId;
    @Basic(optional = false)
    @Column(name = "billboardCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billboardCreatedAt;
    @Basic(optional = false)
    @Column(name = "billboardUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billboardUpdatedAt;
    @Basic(optional = false)
    @Column(name = "billboardStatus")
    private boolean billboardStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billboardId")
    private List<Assistance> assistanceList;
    @JoinColumn(name = "movieId", referencedColumnName = "movieId")
    @ManyToOne(optional = false)
    private Movie movieId;
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    @ManyToOne(optional = false)
    private Room roomId;
    @JoinColumn(name = "scheduleId", referencedColumnName = "scheduleId")
    @ManyToOne(optional = false)
    private Schedule scheduleId;
    @JoinColumn(name = "stateId", referencedColumnName = "stateId")
    @ManyToOne(optional = false)
    private State stateId;

    public Billboard() {
    }

    public Billboard(Integer billboardId) {
        this.billboardId = billboardId;
    }

    public Billboard(Integer billboardId, Date billboardCreatedAt, Date billboardUpdatedAt, boolean billboardStatus) {
        this.billboardId = billboardId;
        this.billboardCreatedAt = billboardCreatedAt;
        this.billboardUpdatedAt = billboardUpdatedAt;
        this.billboardStatus = billboardStatus;
    }

    public Integer getBillboardId() {
        return billboardId;
    }

    public void setBillboardId(Integer billboardId) {
        this.billboardId = billboardId;
    }

    public Date getBillboardCreatedAt() {
        return billboardCreatedAt;
    }

    public void setBillboardCreatedAt(Date billboardCreatedAt) {
        this.billboardCreatedAt = billboardCreatedAt;
    }

    public Date getBillboardUpdatedAt() {
        return billboardUpdatedAt;
    }

    public void setBillboardUpdatedAt(Date billboardUpdatedAt) {
        this.billboardUpdatedAt = billboardUpdatedAt;
    }

    public boolean getBillboardStatus() {
        return billboardStatus;
    }

    public void setBillboardStatus(boolean billboardStatus) {
        this.billboardStatus = billboardStatus;
    }

    @XmlTransient
    public List<Assistance> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(List<Assistance> assistanceList) {
        this.assistanceList = assistanceList;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Schedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        this.scheduleId = scheduleId;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billboardId != null ? billboardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billboard)) {
            return false;
        }
        Billboard other = (Billboard) object;
        if ((this.billboardId == null && other.billboardId != null) || (this.billboardId != null && !this.billboardId.equals(other.billboardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Billboard[ billboardId=" + billboardId + " ]";
    }
    
}
