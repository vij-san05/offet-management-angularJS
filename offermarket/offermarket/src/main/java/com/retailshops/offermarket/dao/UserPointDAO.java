/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;

import com.retailshops.offermarket.model.UserPoint;
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
public class UserPointDAO implements Serializable {

    @PersistenceContext(unitName="offermanagerjpa")
	private EntityManager em;

    public void create(UserPoint userPoint) throws RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
        //    utx.begin();
         //   em = getEntityManager();
            em.persist(userPoint);
        //    utx.commit();
        } catch (Exception ex) {
            try {
          //      utx.rollback();
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

    public void edit(UserPoint userPoint) throws NonexistentEntityException, RollbackFailureException, Exception {
      //  EntityManager em = null;
        try {
         //   utx.begin();
         //   em = getEntityManager();
            userPoint = em.merge(userPoint);
          //  utx.commit();
        } catch (Exception ex) {
            try {
            //    utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
//               // ObjectId id = userPoint.getId();
//                if (findUserPoint(id) == null) {
//                    throw new NonexistentEntityException("The userPoint with id " + id + " no longer exists.");
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
     //   EntityManager em = null;
        try {
          //  utx.begin();
          //  em = getEntityManager();
            UserPoint userPoint;
            try {
                userPoint = em.getReference(UserPoint.class, id);
               // userPoint.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userPoint with id " + id + " no longer exists.", enfe);
            }
            em.remove(userPoint);
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

    public List<UserPoint> findUserPointEntities() {
        return findUserPointEntities(true, -1, -1);
    }

    public List<UserPoint> findUserPointEntities(int maxResults, int firstResult) {
        return findUserPointEntities(false, maxResults, firstResult);
    }

    private List<UserPoint> findUserPointEntities(boolean all, int maxResults, int firstResult) {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserPoint.class));
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

    public UserPoint findUserPoint(ObjectId id) {
      //  EntityManager em = getEntityManager();
        try {
            return em.find(UserPoint.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserPointCount() {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserPoint> rt = cq.from(UserPoint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
