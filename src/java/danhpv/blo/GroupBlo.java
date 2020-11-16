/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblGroup;
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
public class GroupBlo implements Serializable {

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

    public GroupBlo() {
    }

//    public List<TblGroup> getAll() throws Exception {
//        List<TblGroup> list = new ArrayList<>();
//        EntityManager em = emf.createEntityManager();
//        try {
//            Query query = em.createNamedQuery("TblGroup.findAll", TblGroup.class);
//            list = query.getResultList();
//        } finally {
//            em.close();
//        }
//        return list;
//    }
    
    public long getCountAllGroup() throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "Select count (c) from TblGroup c";
            Query query = em.createQuery(sql, TblGroup.class);
            count = (long) query.getSingleResult();
        } finally {
            em.close();
        }
        return count;
    }

    public long getCountAllGroupBySearchName(String nameSearch) throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "Select count (c) from TblGroup c Where c.groupName Like :groupName";
            Query query = em.createQuery(sql, TblGroup.class);
            query.setParameter("groupName", "%" + nameSearch + "%");
            count = (long) query.getSingleResult();
        } finally {
            em.close();
        }
        return count;
    }

    public List<TblGroup> getPaging(int index, int pageSize) throws Exception {
        List<TblGroup> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select t FROM TblGroup t", TblGroup.class);
            query.setFirstResult(index);
            query.setMaxResults(pageSize);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }
    
    public List<TblGroup> getPagingBySearchName(String nameSearch, int index, int pageSize) throws Exception {
        List<TblGroup> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select t FROM TblGroup t Where t.groupName Like :groupName", TblGroup.class);
            query.setParameter("groupName", "%" + nameSearch + "%");
            query.setFirstResult(index);
            query.setMaxResults(pageSize);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public TblGroup findGroupByGroupID(String groupID) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblGroup group = null;
        try {
            String sql = "Select t from TblGroup t where t.groupID = :groupID";
            Query query = em.createQuery(sql, TblGroup.class);
            query.setParameter("groupID", groupID);
            group = (TblGroup) query.getSingleResult();
        } finally {
            em.close();
        }
        return group;
    }

    public List<TblGroup> findGroupByGroupName(String groupName) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblGroup> list = new ArrayList<>();
        try {
            String sql = "Select t from TblGroup t where t.groupName LIKE :groupName";
            Query query = em.createQuery(sql, TblGroup.class);
            query.setParameter("groupName", "%" + groupName + "%");
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public boolean add(TblGroup tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroup u = em.find(TblGroup.class, tu.getGroupID());
            if (u == null) {
                em.getTransaction().begin();
                em.persist(tu);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean differenceTotalMember(String groupID) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroup u = em.find(TblGroup.class, groupID);
            if (u != null) {
                em.getTransaction().begin();
                u.setTotalMember(u.getTotalMember() - 1);
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean addNumRegister(String tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroup u = em.find(TblGroup.class, tu);
            if (u != null) {
                em.getTransaction().begin();
                u.setTotalMember(u.getTotalMember() + 1);
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteGroup(TblGroup tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblGroup u = em.find(TblGroup.class, tu.getGroupID());
            if (u != null) {
                em.getTransaction().begin();
                em.remove(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }
}
