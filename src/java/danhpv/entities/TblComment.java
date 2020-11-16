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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "tblComment", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblComment.findAll", query = "SELECT t FROM TblComment t")
    , @NamedQuery(name = "TblComment.findByCmtID", query = "SELECT t FROM TblComment t WHERE t.cmtID = :cmtID")
    , @NamedQuery(name = "TblComment.findByStudentID", query = "SELECT t FROM TblComment t WHERE t.studentID = :studentID")
    , @NamedQuery(name = "TblComment.findByFullname", query = "SELECT t FROM TblComment t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblComment.findByEmail", query = "SELECT t FROM TblComment t WHERE t.email = :email")
    , @NamedQuery(name = "TblComment.findByContent", query = "SELECT t FROM TblComment t WHERE t.content = :content")
    , @NamedQuery(name = "TblComment.findByTimeComment", query = "SELECT t FROM TblComment t WHERE t.timeComment = :timeComment")})
public class TblComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Column(name = "cmtID", nullable = false)
    private Integer cmtID;
    @Size(max = 8)
    @Column(name = "studentID", length = 8)
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
    @Size(max = 100)
    @Column(name = "content", length = 100)
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeComment", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeComment;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;
    @JoinColumn(name = "memberID", referencedColumnName = "userID")
    @ManyToOne
    private TblUser memberID;

    public TblComment() {
    }

    public TblComment(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public TblComment(Integer cmtID, String email, Date timeComment) {
        this.cmtID = cmtID;
        this.email = email;
        this.timeComment = timeComment;
    }

    public Integer getCmtID() {
        return cmtID;
    }

    public void setCmtID(Integer cmtID) {
        this.cmtID = cmtID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(Date timeComment) {
        this.timeComment = timeComment;
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
        hash += (cmtID != null ? cmtID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblComment)) {
            return false;
        }
        TblComment other = (TblComment) object;
        if ((this.cmtID == null && other.cmtID != null) || (this.cmtID != null && !this.cmtID.equals(other.cmtID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "danhpv.entities.TblComment[ cmtID=" + cmtID + " ]";
    }
    
}
