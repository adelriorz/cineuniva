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

/**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Assistance Class where you can link a user to a certain billboard
**with a User
*/
@Entity
@Table(name = "assistance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assistance.findAll", query = "SELECT a FROM Assistance a")
    , @NamedQuery(name = "Assistance.findByAssistanceId", query = "SELECT a FROM Assistance a WHERE a.assistanceId = :assistanceId")
    , @NamedQuery(name = "Assistance.findByAssistanceCreatedAt", query = "SELECT a FROM Assistance a WHERE a.assistanceCreatedAt = :assistanceCreatedAt")
    , @NamedQuery(name = "Assistance.findByAssistanceUpdatedAt", query = "SELECT a FROM Assistance a WHERE a.assistanceUpdatedAt = :assistanceUpdatedAt")
    , @NamedQuery(name = "Assistance.findByAssistanceStatus", query = "SELECT a FROM Assistance a WHERE a.assistanceStatus = :assistanceStatus")})
public class Assistance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assistanceId")
    private Integer assistanceId;
    @Basic(optional = false)
    @Column(name = "assistanceCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assistanceCreatedAt;
    @Basic(optional = false)
    @Column(name = "assistanceUpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assistanceUpdatedAt;
    @Basic(optional = false)
    @Column(name = "assistanceStatus")
    private boolean assistanceStatus;
    @JoinColumn(name = "billboardId", referencedColumnName = "billboardId")
    @ManyToOne(optional = false)
    private Billboard billboardId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User userId;

    public Assistance() {
    }

    public Assistance(Integer assistanceId) {
        this.assistanceId = assistanceId;
    }

    public Assistance(Integer assistanceId, Date assistanceCreatedAt, Date assistanceUpdatedAt, boolean assistanceStatus) {
        this.assistanceId = assistanceId;
        this.assistanceCreatedAt = assistanceCreatedAt;
        this.assistanceUpdatedAt = assistanceUpdatedAt;
        this.assistanceStatus = assistanceStatus;
    }

    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
    }

    public Date getAssistanceCreatedAt() {
        return assistanceCreatedAt;
    }

    public void setAssistanceCreatedAt(Date assistanceCreatedAt) {
        this.assistanceCreatedAt = assistanceCreatedAt;
    }

    public Date getAssistanceUpdatedAt() {
        return assistanceUpdatedAt;
    }

    public void setAssistanceUpdatedAt(Date assistanceUpdatedAt) {
        this.assistanceUpdatedAt = assistanceUpdatedAt;
    }

    public boolean getAssistanceStatus() {
        return assistanceStatus;
    }

    public void setAssistanceStatus(boolean assistanceStatus) {
        this.assistanceStatus = assistanceStatus;
    }

    public Billboard getBillboardId() {
        return billboardId;
    }

    public void setBillboardId(Billboard billboardId) {
        this.billboardId = billboardId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assistanceId != null ? assistanceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assistance)) {
            return false;
        }
        Assistance other = (Assistance) object;
        if ((this.assistanceId == null && other.assistanceId != null) || (this.assistanceId != null && !this.assistanceId.equals(other.assistanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Assistance[ assistanceId=" + assistanceId + " ]";
    }
    
}
