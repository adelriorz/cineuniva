package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code to create new Municipality Entity
*/
@Entity
@Table(name = "municipality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipality.findAll", query = "SELECT m FROM Municipality m")
    , @NamedQuery(name = "Municipality.findByMunicipalityId", query = "SELECT m FROM Municipality m WHERE m.municipalityId = :municipalityId")
    , @NamedQuery(name = "Municipality.findByMunicipalityName", query = "SELECT m FROM Municipality m WHERE m.municipalityName = :municipalityName")
    , @NamedQuery(name = "Municipality.findByMunicipalityStatus", query = "SELECT m FROM Municipality m WHERE m.municipalityStatus = :municipalityStatus")
    , @NamedQuery(name = "Municipality.findByMunicipalityCreatedAt", query = "SELECT m FROM Municipality m WHERE m.municipalityCreatedAt = :municipalityCreatedAt")
    , @NamedQuery(name = "Municipality.findByMunicipalityUpdatedAt", query = "SELECT m FROM Municipality m WHERE m.municipalityUpdatedAt = :municipalityUpdatedAt")})
public class Municipality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "municipalityId")
    private Integer municipalityId;
    @Basic(optional = false)
    @Column(name = "municipalityName")
    private String municipalityName;
    @Basic(optional = false)
    @Column(name = "municipalityStatus")
    private boolean municipalityStatus;
    @Basic(optional = false)
    @Column(name = "municipalityCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date municipalityCreatedAt;
    @Basic(optional = false)
    @Column(name = "municipalityUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date municipalityUpdatedAt;
    @JoinColumn(name = "stateId", referencedColumnName = "stateId")
    @ManyToOne(optional = false)
    private State stateId;

    public Municipality() {
    }

    public Municipality(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Municipality(Integer municipalityId, String municipalityName, boolean municipalityStatus, Date municipalityCreatedAt, Date municipalityUpdatedAt) {
        this.municipalityId = municipalityId;
        this.municipalityName = municipalityName;
        this.municipalityStatus = municipalityStatus;
        this.municipalityCreatedAt = municipalityCreatedAt;
        this.municipalityUpdatedAt = municipalityUpdatedAt;
    }

    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public boolean getMunicipalityStatus() {
        return municipalityStatus;
    }

    public void setMunicipalityStatus(boolean municipalityStatus) {
        this.municipalityStatus = municipalityStatus;
    }

    public Date getMunicipalityCreatedAt() {
        return municipalityCreatedAt;
    }

    public void setMunicipalityCreatedAt(Date municipalityCreatedAt) {
        this.municipalityCreatedAt = municipalityCreatedAt;
    }

    public Date getMunicipalityUpdatedAt() {
        return municipalityUpdatedAt;
    }

    public void setMunicipalityUpdatedAt(Date municipalityUpdatedAt) {
        this.municipalityUpdatedAt = municipalityUpdatedAt;
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
        hash += (municipalityId != null ? municipalityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipality)) {
            return false;
        }
        Municipality other = (Municipality) object;
        if ((this.municipalityId == null && other.municipalityId != null) || (this.municipalityId != null && !this.municipalityId.equals(other.municipalityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Municipality[ municipalityId=" + municipalityId + " ]";
    }
    
}
