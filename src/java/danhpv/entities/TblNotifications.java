/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "tblNotifications", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNotifications.findAll", query = "SELECT t FROM TblNotifications t")
    , @NamedQuery(name = "TblNotifications.findByNotifiID", query = "SELECT t FROM TblNotifications t WHERE t.notifiID = :notifiID")
    , @NamedQuery(name = "TblNotifications.findByNotifiContent", query = "SELECT t FROM TblNotifications t WHERE t.notifiContent = :notifiContent")
    , @NamedQuery(name = "TblNotifications.findByNotifiTimeCreated", query = "SELECT t FROM TblNotifications t WHERE t.notifiTimeCreated = :notifiTimeCreated")
    , @NamedQuery(name = "TblNotifications.findByIsInternal", query = "SELECT t FROM TblNotifications t WHERE t.isInternal = :isInternal")})
public class TblNotifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "notifiID", nullable = false, length = 50)
    private String notifiID;
    @Size(max = 100)
    @Column(name = "notifiContent", length = 100)
    private String notifiContent;
    @Column(name = "notifiTimeCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notifiTimeCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isInternal", nullable = false)
    private boolean isInternal;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;

    public TblNotifications() {
    }

    public TblNotifications(String notifiID) {
        this.notifiID = notifiID;
    }

    public TblNotifications(String notifiID, boolean isInternal) {
        this.notifiID = notifiID;
        this.isInternal = isInternal;
    }

    public String getNotifiID() {
        return notifiID;
    }

    public void setNotifiID(String notifiID) {
        this.notifiID = notifiID;
    }

    public String getNotifiContent() {
        return notifiContent;
    }

    public void setNotifiContent(String notifiContent) {
        this.notifiContent = notifiContent;
    }

    public Date getNotifiTimeCreated() {
        return notifiTimeCreated;
    }

    public void setNotifiTimeCreated(Date notifiTimeCreated) {
        this.notifiTimeCreated = notifiTimeCreated;
    }

    public boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public TblEvent getEventID() {
        return eventID;
    }

    public void setEventID(TblEvent eventID) {
        this.eventID = eventID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifiID != null ? notifiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNotifications)) {
            return false;
        }
        TblNotifications other = (TblNotifications) object;
        if ((this.notifiID == null && other.notifiID != null) || (this.notifiID != null && !this.notifiID.equals(other.notifiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "danhpv.entities.TblNotifications[ notifiID=" + notifiID + " ]";
    }
    
}
