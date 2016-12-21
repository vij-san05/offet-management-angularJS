/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;

import com.retailshops.offermarket.model.Offer;
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
public class OfferDAO implements Serializable {

    @PersistenceContext(unitName="offermanagerjpa")
	private EntityManager em;
    public void create(Offer offer) throws RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
            //utx.begin();
            //em = getEntityManager();
            em.persist(offer);
          //  utx.commit();
        } catch (Exception ex) {
            try {
               // utx.rollback();
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

    public void edit(Offer offer) throws NonexistentEntityException, RollbackFailureException, Exception {
       // EntityManager em = null;
        try {
           // utx.begin();
           // em = getEntityManager();
            offer = em.merge(offer);
           // utx.commit();
        } catch (Exception ex) {
            try {
            //    utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
//                ObjectId id = offer.getId();
//                if (findOffer(id) == null) {
//                    throw new NonexistentEntityException("The offer with id " + id + " no longer exists.");
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
           // utx.begin();
          //  em = getEntityManager();
            Offer offer;
            try {
                offer = em.getReference(Offer.class, id);
               // offer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The offer with id " + id + " no longer exists.", enfe);
            }
            em.remove(offer);
           // utx.commit();
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

    public List<Offer> findOfferEntities() {
        return findOfferEntities(true, -1, -1);
    }

    public List<Offer> findOfferEntities(int maxResults, int firstResult) {
        return findOfferEntities(false, maxResults, firstResult);
    }

    private List<Offer> findOfferEntities(boolean all, int maxResults, int firstResult) {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Offer.class));
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

    public Offer findOffer(ObjectId id) {
     //   EntityManager em = getEntityManager();
        try {
            return em.find(Offer.class, id);
        } finally {
            em.close();
        }
    }

    public int getOfferCount() {
      //  EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Offer> rt = cq.from(Offer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
