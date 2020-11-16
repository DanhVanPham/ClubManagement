/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "tblUser", catalog = "ClubManagement", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t")
    , @NamedQuery(name = "TblUser.findByUserID", query = "SELECT t FROM TblUser t WHERE t.userID = :userID")
    , @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password")
    , @NamedQuery(name = "TblUser.findByFullname", query = "SELECT t FROM TblUser t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblUser.findByEmail", query = "SELECT t FROM TblUser t WHERE t.email = :email")
    , @NamedQuery(name = "TblUser.findByAvatar", query = "SELECT t FROM TblUser t WHERE t.avatar = :avatar")
    , @NamedQuery(name = "TblUser.findByGetNotification", query = "SELECT t FROM TblUser t WHERE t.getNotification = :getNotification")
    , @NamedQuery(name = "TblUser.findByStatus", query = "SELECT t FROM TblUser t WHERE t.status = :status")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "userID", nullable = false, length = 8)
    private String userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Size(max = 30)
    @Column(name = "fullname", length = 30)
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Size(max = 200)
    @Column(name = "avatar", length = 200)
    private String avatar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "getNotification", nullable = false)
    private boolean getNotification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status", nullable = false)
    private boolean status;
    @JoinColumn(name = "roleId", referencedColumnName = "roleID")
    @ManyToOne
    private TblRole roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaderId")
    private Collection<TblGroup> tblGroupCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblComment> tblCommentCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblEventDetail> tblEventDetailCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblGroupDetail> tblGroupDetailCollection;

    public TblUser() {
    }

    public TblUser(String userID) {
        this.userID = userID;
    }

    public TblUser(String userID, String password, String email, boolean getNotification, boolean status) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.getNotification = getNotification;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean getGetNotification() {
        return getNotification;
    }

    public void setGetNotification(boolean getNotification) {
        this.getNotification = getNotification;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TblRole getRoleId() {
        return roleId;
    }

    public void setRoleId(TblRole roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<TblGroup> getTblGroupCollection() {
        return tblGroupCollection;
    }

    public void setTblGroupCollection(Collection<TblGroup> tblGroupCollection) {
        this.tblGroupCollection = tblGroupCollection;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }

    @XmlTransient
    public Collection<TblEventDetail> getTblEventDetailCollection() {
        return tblEventDetailCollection;
    }

    public void setTblEventDetailCollection(Collection<TblEventDetail> tblEventDetailCollection) {
        this.tblEventDetailCollection = tblEventDetailCollection;
    }

    @XmlTransient
    public Collection<TblGroupDetail> getTblGroupDetailCollection() {
        return tblGroupDetailCollection;
    }

    public void setTblGroupDetailCollection(Collection<TblGroupDetail> tblGroupDetailCollection) {
        this.tblGroupDetailCollection = tblGroupDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "danhpv.entities.TblUser[ userID=" + userID + " ]";
    }
    
}
