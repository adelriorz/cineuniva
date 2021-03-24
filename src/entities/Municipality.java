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
@Table(name = "municipality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipality.findAll", query = "SELECT m FROM Municipality m")
    , @NamedQuery(name = "Municipality.findByMunicipalityId", query = "SELECT m FROM Municipality m WHERE m.municipalityPK.municipalityId = :municipalityId")
    , @NamedQuery(name = "Municipality.findByMunicipalityName", query = "SELECT m FROM Municipality m WHERE m.municipalityName = :municipalityName")
    , @NamedQuery(name = "Municipality.findByMunicipalityStatus", query = "SELECT m FROM Municipality m WHERE m.municipalityStatus = :municipalityStatus")
    , @NamedQuery(name = "Municipality.findByMunicipalityCreatedAt", query = "SELECT m FROM Municipality m WHERE m.municipalityCreatedAt = :municipalityCreatedAt")
    , @NamedQuery(name = "Municipality.findByMunicipalityUpdatedAt", query = "SELECT m FROM Municipality m WHERE m.municipalityUpdatedAt = :municipalityUpdatedAt")
    , @NamedQuery(name = "Municipality.findByStateId", query = "SELECT m FROM Municipality m WHERE m.municipalityPK.stateId = :stateId")})
public class Municipality implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MunicipalityPK municipalityPK;
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
    @JoinColumn(name = "stateId", referencedColumnName = "stateId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private State state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipality")
    private List<Assistance> assistanceList;

    public Municipality() {
    }

    public Municipality(MunicipalityPK municipalityPK) {
        this.municipalityPK = municipalityPK;
    }

    public Municipality(MunicipalityPK municipalityPK, String municipalityName, boolean municipalityStatus, Date municipalityCreatedAt, Date municipalityUpdatedAt) {
        this.municipalityPK = municipalityPK;
        this.municipalityName = municipalityName;
        this.municipalityStatus = municipalityStatus;
        this.municipalityCreatedAt = municipalityCreatedAt;
        this.municipalityUpdatedAt = municipalityUpdatedAt;
    }

    public Municipality(int municipalityId, int stateId) {
        this.municipalityPK = new MunicipalityPK(municipalityId, stateId);
    }

    public MunicipalityPK getMunicipalityPK() {
        return municipalityPK;
    }

    public void setMunicipalityPK(MunicipalityPK municipalityPK) {
        this.municipalityPK = municipalityPK;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
        hash += (municipalityPK != null ? municipalityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipality)) {
            return false;
        }
        Municipality other = (Municipality) object;
        if ((this.municipalityPK == null && other.municipalityPK != null) || (this.municipalityPK != null && !this.municipalityPK.equals(other.municipalityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Municipality[ municipalityPK=" + municipalityPK + " ]";
    }
    
}
