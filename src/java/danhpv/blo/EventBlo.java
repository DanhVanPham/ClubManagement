/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblEvent;
import danhpv.entities.TblUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class EventBlo implements Serializable {

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

    public List<TblEvent> getAll() throws Exception {
        List<TblEvent> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("TblEvent.findAll", TblEvent.class);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public long getCountAllEvent(boolean checkInternal) throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            if (checkInternal) {
                String sql = "Select count (c) from TblEvent c";
                Query query = em.createQuery(sql, TblEvent.class);
                count = (long) query.getSingleResult();
            } else {
                String sql = "Select count (c) from TblEvent c Where c.isInternal = :isInternal";
                Query query = em.createQuery(sql, TblEvent.class);
                query.setParameter("isInternal", checkInternal);
                count = (long) query.getSingleResult();
            }
        } finally {
            em.close();
        }
        return count;
    }

    public long getCountAllEventBySearchName(String nameSearch, boolean checkInternal) throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            if (checkInternal) {
                String sql = "Select count (c) from TblEvent c Where c.eventName Like :eventName";
                Query query = em.createQuery(sql, TblEvent.class);
                query.setParameter("eventName", "%" + nameSearch + "%");
                count = (long) query.getSingleResult();
            } else {
                String sql = "Select count (c) from TblEvent c Where c.eventName Like :eventName and c.isInternal = :isInternal";
                Query query = em.createQuery(sql, TblEvent.class);
                query.setParameter("eventName", "%" + nameSearch + "%");
                query.setParameter("isInternal", checkInternal);
                count = (long) query.getSingleResult();
            }
        } finally {
            em.close();
        }
        return count;
    }

    public List<TblEvent> getPaging(int index, int pageSize, boolean checkInternal) throws Exception {
        List<TblEvent> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            if (checkInternal) {
                Query query = em.createQuery("Select t FROM TblEvent t", TblEvent.class);
                query.setFirstResult(index);
                query.setMaxResults(pageSize);
                list = query.getResultList();
            } else {
                String sql = "Select t FROM TblEvent t Where t.isInternal = :isInternal";
                Query query = em.createQuery(sql, TblEvent.class);
                query.setParameter("isInternal", checkInternal);
                query.setFirstResult(index);
                query.setMaxResults(pageSize);
                list = query.getResultList();

            }
        } finally {
            em.close();
        }
        return list;
    }

    public List<TblEvent> getPagingBySearchName(String nameSearch, int index, int pageSize, boolean checkInternal) throws Exception {
        List<TblEvent> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            if (checkInternal) {
                Query query = em.createQuery("Select t FROM TblEvent t Where t.eventName Like :eventName", TblEvent.class);
                query.setParameter("eventName", "%" + nameSearch + "%");
                query.setFirstResult(index);
                query.setMaxResults(pageSize);
                list = query.getResultList();
            } else {
                String sql = "Select t FROM TblEvent t Where t.eventName Like :eventName and t.isInternal = :isInternal ";
                Query query = em.createQuery(sql, TblEvent.class);
                query.setParameter("eventName", "%" + nameSearch + "%");
                query.setParameter("isInternal", checkInternal);
                query.setFirstResult(index);
                query.setMaxResults(pageSize);
                list = query.getResultList();
            }
        } finally {
            em.close();
        }
        return list;
    }

    public List<TblEvent> findEventByEventName(String eventName) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> list = new ArrayList<>();
        try {
            String sql = "Select t from TblEvent t where t.eventName LIKE :eventName";
            Query query = em.createQuery(sql, TblUser.class);
            query.setParameter("eventName", "%" + eventName + "%");
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public TblEvent findEventByEventID(String eventID) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblEvent event = null;
        try {
            String sql = "Select t from TblEvent t where t.eventID = :eventID";
            Query query = em.createQuery(sql, TblEvent.class);
            query.setParameter("eventID", eventID);
            event = (TblEvent) query.getSingleResult();
        } finally {
            em.close();
        }
        return event;
    }

    public List<TblEvent> findAllEventInTheInterval(Date dateFrom, Date dateTo) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> listEvents = new ArrayList<>();
        try {
            String sql = "Select t From TblEvent t Where t.timeStartEvent >= :timeStartEvent and t.timeCloseEvent <= :timeCloseEvent order by t.numRegister desc";
            Query query = em.createQuery(sql, TblEvent.class);
            query.setParameter("timeStartEvent", dateFrom);
            query.setParameter("timeCloseEvent", dateTo);
            listEvents = query.getResultList();
        } finally {
            em.close();
        }
        return listEvents;
    }

    public long getCountAllEventInTheInterval(Date dateFrom, Date dateTo) throws Exception {
        long count;
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "Select count (t) from TblEvent t Where t.timeStartEvent >= :timeStartEvent and t.timeCloseEvent <= :timeCloseEvent";
            Query query = em.createQuery(sql, TblEvent.class);
            query.setParameter("timeStartEvent", dateFrom);
            query.setParameter("timeCloseEvent", dateTo);
            count = (long) query.getSingleResult();
        } finally {
            em.close();
        }
        return count;
    }

    public List<TblEvent> getPagingEventInterval(Date dateFrom, Date dateTo, int index, int pageSize) throws Exception {
        List<TblEvent> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select t from TblEvent t Where t.timeStartEvent >= :timeStartEvent and t.timeCloseEvent <= :timeCloseEvent order by t.numRegister desc", TblEvent.class);
            query.setParameter("timeStartEvent", dateFrom);
            query.setParameter("timeCloseEvent", dateTo);
            query.setFirstResult(index);
            query.setMaxResults(pageSize);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public boolean updateNumRegister(TblEvent tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblEvent u = em.find(TblEvent.class, tu.getEventID());
            if (u != null) {
                em.getTransaction().begin();
                u.setNumRegister(u.getNumRegister() + 1);
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteEvent(TblEvent tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblEvent u = em.find(TblEvent.class, tu.getEventID());
            if (u != null) {
                em.getTransaction().begin();
                u.setEventStatus("DELETED");
                em.merge(u);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
        }
        return false;
    }

    public boolean addEvent(TblEvent tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblEvent u = em.find(TblEvent.class, tu.getEventID());
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
}
