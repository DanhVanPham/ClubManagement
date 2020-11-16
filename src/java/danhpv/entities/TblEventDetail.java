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
@Table(name = "tblEventDetail", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEventDetail.findAll", query = "SELECT t FROM TblEventDetail t")
    , @NamedQuery(name = "TblEventDetail.findByEvDetailID", query = "SELECT t FROM TblEventDetail t WHERE t.evDetailID = :evDetailID")
    , @NamedQuery(name = "TblEventDetail.findByStudentID", query = "SELECT t FROM TblEventDetail t WHERE t.studentID = :studentID")
    , @NamedQuery(name = "TblEventDetail.findByFullname", query = "SELECT t FROM TblEventDetail t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblEventDetail.findByEmail", query = "SELECT t FROM TblEventDetail t WHERE t.email = :email")
    , @NamedQuery(name = "TblEventDetail.findByRegisterTime", query = "SELECT t FROM TblEventDetail t WHERE t.registerTime = :registerTime")
    , @NamedQuery(name = "TblEventDetail.findByStatus", query = "SELECT t FROM TblEventDetail t WHERE t.status = :status")})
public class TblEventDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "evDetailID", nullable = false, length = 50)
    private String evDetailID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "studentID", nullable = false, length = 8)
    private String studentID;
    @Size(max = 30)
    @Column(name = "fullname", length = 30)
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registerTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;
    @JoinColumn(name = "memberID", referencedColumnName = "userID")
    @ManyToOne
    private TblUser memberID;

    public TblEventDetail() {
    }

    public TblEventDetail(String evDetailID) {
        this.evDetailID = evDetailID;
    }

    public TblEventDetail(String evDetailID, String studentID, String email, Date registerTime, String status) {
        this.evDetailID = evDetailID;
        this.studentID = studentID;
        this.email = email;
        this.registerTime = registerTime;
        this.status = status;
    }

    public String getEvDetailID() {
        return evDetailID;
    }

    public void setEvDetailID(String evDetailID) {
        this.evDetailID = evDetailID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TblEvent getEventID() {
        return eventID;
    }

    public void setEventID(TblEvent eventID) {
        this.eventID = eventID;
    }

    public TblUser getMemberID() {
        return memberID;
    }

    public void setMemberID(TblUser memberID) {
        this.memberID = memberID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evDetailID != null ? evDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEventDetail)) {
            return false;
        }
        TblEventDetail other = (TblEventDetail) object;
        if ((this.evDetailID == null && other.evDetailID != null) || (this.evDetailID != null && !this.evDetailID.equals(other.evDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "danhpv.entities.TblEventDetail[ evDetailID=" + evDetailID + " ]";
    }
    
}
