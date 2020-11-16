/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblEvent;
import danhpv.entities.TblEventDetail;
import danhpv.entities.TblUser;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
public class EventDetailBlo implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PRJ321_FALL2020_AssignmentPU");

    public EventDetailBlo() {
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

    public boolean registerEvent(TblUser user, HashMap<String, TblEvent> lsEvent, TblUser member) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EventBlo eventBlo = new EventBlo();
            for (TblEvent event : lsEvent.values()) {
                TblEventDetail eventDetail = new TblEventDetail();
                String date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                String convertDate = date.replace(" ", "-");
                eventDetail.setEvDetailID(event.getEventID() + "-" + user.getUserID() + "-" + convertDate);
                eventDetail.setEventID(new TblEvent(event.getEventID()));
                if (member != null) {
                    eventDetail.setMemberID(member);
                }
                eventDetail.setStudentID(user.getUserID());
                eventDetail.setFullname(user.getFullname());
                eventDetail.setEmail(user.getEmail());
                eventDetail.setRegisterTime(new Date());
                eventDetail.setStatus("REGISTER");
                em.persist(eventDetail);
                eventBlo.updateNumRegister(event);
            }
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }

    public List<TblEventDetail> getListEventDetailByEventID(String eventID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> list = new ArrayList<>();
        try {
            String sql1 = "select e From TblEventDetail e \n"
                    + "Where  e.eventID.eventID LIKE :eventID";
            Query query = em.createQuery(sql1, TblEventDetail.class);
            query.setParameter("eventID", eventID);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public boolean checkUserIDRegisteredEvent(String eventID, String userID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> list = new ArrayList<>();
        try {
            String sql1 = "select e From TblEventDetail e \n"
                    + "Where  e.eventID.eventID LIKE :eventID and e.studentID = :userID";
            Query query = em.createQuery(sql1, TblEventDetail.class);
            query.setParameter("eventID", eventID);
            query.setParameter("userID", userID);
            list = query.getResultList();
        } finally {
            em.close();
        }
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkEmailRegisteredEvent(String eventID, String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> list = new ArrayList<>();
        try {
            String sql1 = "select e From TblEventDetail e \n"
                    + "Where  e.eventID.eventID LIKE :eventID and e.email = :email";
            Query query = em.createQuery(sql1, TblEventDetail.class);
            query.setParameter("eventID", eventID);
            query.setParameter("email", email);
            list = query.getResultList();
        } finally {
            em.close();
        }
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<TblEventDetail> getListEventDetailByEventIDAndUserID(String eventID, TblUser user) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> list = new ArrayList<>();
        try {
            String sql1 = "select e From TblEventDetail e \n"
                    + "Where  e.eventID.eventID LIKE :eventID and e.studentID LIKE :studentID";
            Query query = em.createQuery(sql1, TblEventDetail.class);
            query.setParameter("eventID", eventID);
            query.setParameter("studentID", user.getUserID());
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public List<TblEventDetail> getListEventDetailByUserID(String userID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> list = new ArrayList<>();
        try {
            String sql1 = "SELECT t FROM TblEventDetail t WHERE t.studentID = :studentID";
            Query query = em.createQuery(sql1, TblEventDetail.class);
            query.setParameter("studentID", userID);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }
}
