/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;

import com.retailshops.offermarket.model.Point;
import com.retailshops.offermarket.dao.exceptions.NonexistentEntityException;
import com.retailshops.offermarket.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import org.bson.types.ObjectId;

/**
 *
 * @author Vijay
 */
public class PointDAO implements Serializable {

    @PersistenceContext(unitName="offermanagerjpa")
	private EntityManager em;

    public void create(Point point) throws RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
           // utx.begin();
           // em = getEntityManager();
            em.persist(point);
          //  utx.commit();
        } catch (Exception ex) {
            try {
            //    utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Point point) throws NonexistentEntityException, RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
           // utx.begin();
         //   em = getEntityManager();
            point = em.merge(point);
         //   utx.commit();
        } catch (Exception ex) {
            try {
           //     utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
//                ObjectId id = point.getId();
//                if (findPoint(id) == null) {
//                    throw new NonexistentEntityException("The point with id " + id + " no longer exists.");
//                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjectId id) throws NonexistentEntityException, RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
         //   utx.begin();
         //   em = getEntityManager();
            Point point;
            try {
                point = em.getReference(Point.class, id);
              //  point.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The point with id " + id + " no longer exists.", enfe);
            }
            em.remove(point);
          //  utx.commit();
        } catch (Exception ex) {
            try {
            //    utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Point> findPointEntities() {
        return findPointEntities(true, -1, -1);
    }

    public List<Point> findPointEntities(int maxResults, int firstResult) {
        return findPointEntities(false, maxResults, firstResult);
    }

    private List<Point> findPointEntities(boolean all, int maxResults, int firstResult) {
     //   EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Point.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Point findPoint(ObjectId id) {
      //  EntityManager em = getEntityManager();
        try {
            return em.find(Point.class, id);
        } finally {
            em.close();
        }
    }

    public int getPointCount() {
     //   EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Point> rt = cq.from(Point.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
