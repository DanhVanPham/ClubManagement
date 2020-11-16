/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblNotifications;
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
public class NotificationsBlo implements Serializable {

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

    public NotificationsBlo() {
    }

    public boolean addNotifications(TblNotifications tu) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblNotifications u = em.find(TblNotifications.class, tu.getNotifiID());
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

    public List<TblNotifications> getNotifications(Boolean isInternal) throws Exception {
        List<TblNotifications> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            if (!isInternal) {
                Query query = em.createNamedQuery("TblNotifications.findByIsInternal", TblNotifications.class);
                query.setParameter("isInternal", isInternal);
                list = query.getResultList();
            } else {
                Query query = em.createNamedQuery("TblNotifications.findAll", TblNotifications.class);
                list = query.getResultList();
            }
        } finally {
            em.close();
        }
        return list;
    }
}
