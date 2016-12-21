/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;

import com.retailshops.offermarket.model.Log;
import com.retailshops.offermarket.dao.exceptions.NonexistentEntityException;
import com.retailshops.offermarket.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import org.bson.types.ObjectId;

/**
 *
 * @author Vijay
 */


public class LogDAO implements Serializable {

    

    
    public LogDAO(EntityManagerFactory emf) {
       
        this.emf = emf;
    }
   
    private EntityManagerFactory emf = null;
    private  EntityTransaction tx =null;
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Log log) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
           // utx.begin();
            em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
            em.persist(log);
            
            tx.commit();
           
        } catch (Exception ex) {
            try {
                tx.rollback();
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

    public void edit(Log log) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
           em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
            log = em.merge(log);
           tx.commit();
        } catch (Exception ex) {
            try {
           tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjectId id = log.getId();
                if (findLog(id) == null) {
                    throw new NonexistentEntityException("The log with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjectId id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
           em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
            Log log;
            try {
                log = em.getReference(Log.class, id);
                log.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The log with id " + id + " no longer exists.", enfe);
            }
            em.remove(log);
              tx.commit();
        } catch (Exception ex) {
            try {
              tx.rollback();
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

    public List<Log> findLogEntities() {
        return findLogEntities(true, -1, -1);
    }

    public List<Log> findLogEntities(int maxResults, int firstResult) {
        return findLogEntities(false, maxResults, firstResult);
    }

    private List<Log> findLogEntities(boolean all, int maxResults, int firstResult) {
        
        System.out.print("requesting all");
        
        
        EntityManager em = getEntityManager();
        try {
           //// CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
          //  cq.select(cq.from(Log.class));
         //   Query q = em.createQuery(cq);
         //   if (!all) {
         //       q.setMaxResults(maxResults);
         //       q.setFirstResult(firstResult);
         //   }
            
            
            
           //return q.getResultList();
            
            List<Log> logs = em
            .createQuery( "from Log" , Log.class )
            .getResultList();
            return logs;
            
            
        } finally {
            em.close();
        }
    }

    public Log findLog(ObjectId id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Log.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Log> rt = cq.from(Log.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
