/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;

import com.retailshops.offermarket.model.Reward;
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
public class RewardDAO implements Serializable {

    @PersistenceContext(unitName="offermanagerjpa")
	private EntityManager em;
    public void create(Reward reward) throws RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
           // utx.begin();
           // em = getEntityManager();
            em.persist(reward);
           // utx.commit();
        } catch (Exception ex) {
            try {
              //  utx.rollback();
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

    public void edit(Reward reward) throws NonexistentEntityException, RollbackFailureException, Exception {
      //  EntityManager em = null;
        try {
           // utx.begin();
           // em = getEntityManager();
            reward = em.merge(reward);
          //  utx.commit();
        } catch (Exception ex) {
            try {
              //  utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
//                ObjectId id = reward.getId();
//                if (findReward(id) == null) {
//                    throw new NonexistentEntityException("The reward with id " + id + " no longer exists.");
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
          //  utx.begin();
          //  em = getEntityManager();
            Reward reward;
            try {
                reward = em.getReference(Reward.class, id);
//                reward.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reward with id " + id + " no longer exists.", enfe);
            }
            em.remove(reward);
          //  utx.commit();
        } catch (Exception ex) {
            try {
             //   utx.rollback();
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

    public List<Reward> findRewardEntities() {
        return findRewardEntities(true, -1, -1);
    }

    public List<Reward> findRewardEntities(int maxResults, int firstResult) {
        return findRewardEntities(false, maxResults, firstResult);
    }

    private List<Reward> findRewardEntities(boolean all, int maxResults, int firstResult) {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reward.class));
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

    public Reward findReward(ObjectId id) {
       // EntityManager em = getEntityManager();
        try {
            return em.find(Reward.class, id);
        } finally {
            em.close();
        }
    }

    public int getRewardCount() {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reward> rt = cq.from(Reward.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
