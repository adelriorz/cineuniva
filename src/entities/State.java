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
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
    , @NamedQuery(name = "State.findByStateId", query = "SELECT s FROM State s WHERE s.stateId = :stateId")
    , @NamedQuery(name = "State.findByStateName", query = "SELECT s FROM State s WHERE s.stateName = :stateName")
    , @NamedQuery(name = "State.findByStateStatus", query = "SELECT s FROM State s WHERE s.stateStatus = :stateStatus")
    , @NamedQuery(name = "State.findByStateCreatedAt", query = "SELECT s FROM State s WHERE s.stateCreatedAt = :stateCreatedAt")
    , @NamedQuery(name = "State.findByStateUpdatedAt", query = "SELECT s FROM State s WHERE s.stateUpdatedAt = :stateUpdatedAt")})
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stateId")
    private Integer stateId;
    @Basic(optional = false)
    @Column(name = "stateName")
    private String stateName;
    @Basic(optional = false)
    @Column(name = "stateStatus")
    private boolean stateStatus;
    @Basic(optional = false)
    @Column(name = "stateCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stateCreatedAt;
    @Basic(optional = false)
    @Column(name = "stateUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stateUpdatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private List<Municipality> municipalityList;

    public State() {
    }

    public State(Integer stateId) {
        this.stateId = stateId;
    }

    public State(Integer stateId, String stateName, boolean stateStatus, Date stateCreatedAt, Date stateUpdatedAt) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateStatus = stateStatus;
        this.stateCreatedAt = stateCreatedAt;
        this.stateUpdatedAt = stateUpdatedAt;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public boolean getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(boolean stateStatus) {
        this.stateStatus = stateStatus;
    }

    public Date getStateCreatedAt() {
        return stateCreatedAt;
    }

    public void setStateCreatedAt(Date stateCreatedAt) {
        this.stateCreatedAt = stateCreatedAt;
    }

    public Date getStateUpdatedAt() {
        return stateUpdatedAt;
    }

    public void setStateUpdatedAt(Date stateUpdatedAt) {
        this.stateUpdatedAt = stateUpdatedAt;
    }

    @XmlTransient
    public List<Municipality> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(List<Municipality> municipalityList) {
        this.municipalityList = municipalityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.State[ stateId=" + stateId + " ]";
    }
    
}
