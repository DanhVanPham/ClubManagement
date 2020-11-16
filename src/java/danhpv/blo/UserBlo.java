/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblGroupDetail;
import danhpv.entities.TblUser;
import java.io.Serializable;
import java.util.ArrayList;
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
public class UserBlo implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PRJ321_FALL2020_AssignmentPU");

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

    public TblUser checkLogin(String u, String p) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblUser user = null;
        try {
            String sql = "Select t from TblUser t where t.userID = :userID and t.password = :password";
            Query query = em.createQuery(sql, TblUser.class);
            query.setParameter("userID", u);
            query.setParameter("password", p);
            user = (TblUser) query.getSingleResult();
        } finally {
            em.close();
        }
        return user;
    }

    public TblUser findUserByUserID(String userID) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblUser user = null;
        try {
            String sql = "Select t from TblUser t where t.userID = :userID";
            Query query = em.createQuery(sql, TblUser.class);
            query.setParameter("userID", userID);
            user = (TblUser) query.getSingleResult();
        } finally {
            em.close();
        }
        return user;
    }

    public List<TblUser> findUserByUserName(String userName) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblUser> list = new ArrayList<>();
        try {
            String sql = "Select t from TblUser t where t.fullname LIKE :fullname";
            Query query = em.createQuery(sql, TblUser.class);
            query.setParameter("fullname", "%" + userName + "%");
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public TblUser checkConfirmEmail(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblUser tbl = null;
        try {
            Query query = em.createQuery("SELECT t FROM TblUser t WHERE t.email = :email", TblUser.class);
            query.setParameter("email", email);
            tbl = (TblUser) query.getSingleResult();
        } finally {
            em.close();
        }
        return tbl;
    }

    public List<TblUser> getAll() throws Exception {
        List<TblUser> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("TblUser.findAll", TblUser.class);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }
    
    public long getCountAllUser() throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "Select count (c) from TblUser c";
            Query query = em.createQuery(sql, TblUser.class);
            count = (long) query.getSingleResult();
        } finally {
            em.close();
        }
        return count;
    }

    public long getCountAllUserBySearchName(String nameSearch) throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "Select count (c) from TblUser c Where c.fullname Like :fullname";
            Query query = em.createQuery(sql, TblUser.class);
            query.setParameter("fullname", "%" + nameSearch + "%");
            count = (long) query.getSingleResult();
        } finally {
            em.close();
        }
        return count;
    }

    public List<TblUser> getPaging(int index, int pageSize) throws Exception {
        List<TblUser> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select t FROM TblUser t", TblUser.class);
            query.setFirstResult(index);
            query.setMaxResults(pageSize);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }
    
    public List<TblUser> getPagingBySearchName(String nameSearch, int index, int pageSize) throws Exception {
        List<TblUser> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select t FROM TblUser t Where t.fullname Like :fullname", TblUser.class);
            query.setParameter("fullname", "%" + nameSearch + "%");
            query.setFirstResult(index);
            query.setMaxResults(pageSize);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }


    public boolean changeStatus(TblUser tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblUser u = em.find(TblUser.class, tu.getUserID());
            if (u != null) {
                em.getTransaction().begin();
                em.merge(tu);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean changeRole(TblUser tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblUser u = em.find(TblUser.class, tu.getUserID());
            if (u != null) {
                em.getTransaction().begin();
                em.merge(tu);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean updateProfile(TblUser tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblUser u = em.find(TblUser.class, tu.getUserID());
            if (u != null) {
                em.getTransaction().begin();
                u.setFullname(tu.getFullname());
                u.setEmail(tu.getEmail());
                u.setAvatar(tu.getAvatar());
                u.setGetNotification(tu.getGetNotification());
                u.setStatus(tu.getStatus());
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public List<String> getListMemberAdd(List<TblUser> listGroupDetail, String leaderID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<String> list = new ArrayList<>();
        try {
            if (listGroupDetail != null && listGroupDetail.size() > 0) {
                List<String> listN = new ArrayList<>();
                for (TblUser tblUser : listGroupDetail) {
                    listN.add(tblUser.getUserID());
                }
                String sql2 = "select u.userID From TblUser u\n"
                        + "where u.userID NOT IN :listN and u.status = :status and u.roleId.roleName LIKE :roleName";
                Query query = em.createQuery(sql2, TblUser.class);
                query.setParameter("listN", listN);
                query.setParameter("status", true);
                query.setParameter("roleName", "Member");
                list = query.getResultList();
            } else {
                String sql2 = "select u.userID From TblUser u\n"
                        + "where u.userID != :leaderID and u.status = :status";
                Query query = em.createQuery(sql2, TblUser.class);
                query.setParameter("leaderID", leaderID);
                query.setParameter("status", true);
                list = query.getResultList();
            }
        } finally {
            em.close();
        }
        return list;
    }

    public boolean add(TblUser tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
//            TblUser u = em.find(TblUser.class, tu.getUserID());
//            if (u == null) {
            em.getTransaction().begin();
            em.persist(tu);
            em.getTransaction().commit();
            return true;
//update
//            if(u != null){
//                em.getTransaction().begin();
//                em.merge(tu);
//                em.getTransaction().commit();
//            }
        } finally {
            em.close();
        }
    }
}
