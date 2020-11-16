/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblGroup;
import danhpv.entities.TblGroupDetail;
import danhpv.entities.TblUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author DELL
 */
public class GroupDetailBlo implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PRJ321_FALL2020_AssignmentPU");

    public GroupDetailBlo() {
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<TblGroupDetail> getGroupJoinByUserID(TblUser userID, String status) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblGroupDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT t FROM TblGroupDetail t WHERE t.memberID = :memberID and t.status = :status";
            Query query = em.createQuery(sql, TblGroupDetail.class);
            query.setParameter("memberID", userID);
            query.setParameter("status", status);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public List<TblGroupDetail> getGroupDetailsByGroupID(String groupID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblGroupDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT t FROM TblGroupDetail t WHERE t.groupID.groupID = :groupID";
            Query query = em.createQuery(sql, TblGroupDetail.class);
            query.setParameter("groupID", groupID);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public TblGroupDetail findGroupDetailByGroupIDAndMemID(String groupID, String memberID, String status) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblGroupDetail groupDetails = new TblGroupDetail();
        try {
            if (status != null) {
                String sql = "SELECT t FROM TblGroupDetail t WHERE t.groupID.groupID = :groupID and t.memberID.userID = :memberID and t.status LIKE :status";
                Query query = em.createQuery(sql, TblGroupDetail.class);
                query.setParameter("groupID", groupID);
                query.setParameter("memberID", memberID);
                query.setParameter("status", status);
                groupDetails = (TblGroupDetail) query.getSingleResult();
            }else {
                String sql = "SELECT t FROM TblGroupDetail t WHERE t.groupID.groupID = :groupID and t.memberID.userID = :memberID";
                Query query = em.createQuery(sql, TblGroupDetail.class);
                query.setParameter("groupID", groupID);
                query.setParameter("memberID", memberID);
                groupDetails = (TblGroupDetail) query.getSingleResult();
            }
        } finally {
            em.close();
        }
        return groupDetails;
    }

    public List<String> getListMemberAdd(TblGroup group, String leaderID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblUser> list = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        try {
            String sql1 = "select g.memberID From TblGroupDetail g \n"
                    + "Where  g.groupID.groupID LIKE :groupID";
            Query query = em.createQuery(sql1, TblGroupDetail.class);
            query.setParameter("groupID", group.getGroupID());
            list = query.getResultList();
            UserBlo userBlo = new UserBlo();
            ls = userBlo.getListMemberAdd(list, leaderID);
        } finally {
            em.close();
        }
        return ls;
    }

    public TblGroupDetail getGroupDetailByGroupDetailID(String groupDetailID) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroupDetail u = em.find(TblGroupDetail.class, groupDetailID);
            return u;
        } finally {
            em.close();
        }
    }

    public boolean leaveTheGroup(TblGroupDetail group) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroupDetail u = em.find(TblGroupDetail.class, group.getGroupDetailID());
            if (u != null) {
                em.getTransaction().begin();
                u.setStatus(group.getStatus());
                u.setRemoveTime(group.getRemoveTime());
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteGroupDetails(TblGroupDetail groupDetail) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(groupDetail)) {
                groupDetail = em.merge(groupDetail);
            }
            em.remove(groupDetail);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }

    public boolean removeMemFromGroup(TblGroupDetail groupDetail) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroupDetail u = em.find(TblGroupDetail.class, groupDetail.getGroupDetailID());
            if (u != null) {
                em.getTransaction().begin();
                u.setStatus("BLOCK");
                Date date = new Date();
                u.setRemoveTime(date);
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean registerMember(HashMap<String, TblUser> lsMem, String groupID) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            GroupBlo groupBlo = new GroupBlo();
            for (TblUser member : lsMem.values()) {
                Date date = new Date();
                TblGroupDetail groupDetail = new TblGroupDetail();
                groupDetail.setGroupDetailID(groupID + "-" + member.getUserID());
                groupDetail.setGroupID(new TblGroup(groupID));
                groupDetail.setMemberID(new TblUser(member.getUserID()));
                groupDetail.setStatus("ACTIVE");
                groupDetail.setAddTime(date);
                em.persist(groupDetail);
                groupBlo.addNumRegister(groupID);
            }
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }
}
