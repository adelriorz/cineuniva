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
public class MunicipalityPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "municipalityId")
    private int municipalityId;
    @Basic(optional = false)
    @Column(name = "stateId")
    private int stateId;

    public MunicipalityPK() {
    }

    public MunicipalityPK(int municipalityId, int stateId) {
        this.municipalityId = municipalityId;
        this.stateId = stateId;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) municipalityId;
        hash += (int) stateId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipalityPK)) {
            return false;
        }
        MunicipalityPK other = (MunicipalityPK) object;
        if (this.municipalityId != other.municipalityId) {
            return false;
        }
        if (this.stateId != other.stateId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MunicipalityPK[ municipalityId=" + municipalityId + ", stateId=" + stateId + " ]";
    }
    
}
