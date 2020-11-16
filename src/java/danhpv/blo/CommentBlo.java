/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.blo;

import danhpv.entities.TblComment;
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
public class CommentBlo implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PRJ321_FALL2020_AssignmentPU");

    public CommentBlo() {
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

    public boolean addComment(TblComment comment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }
    
    public List<TblComment> getListMemberAdd(String eventID) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblComment> list = new ArrayList<>();
        try {
            String sql1 = "select c From TblComment c \n"
                    + "Where  c.eventID.eventID LIKE :eventID";
            Query query = em.createQuery(sql1, TblComment.class);
            query.setParameter("eventID", eventID);
            list = query.getResultList();
        } finally {
            em.close();
        }
        return list;
    }
}
