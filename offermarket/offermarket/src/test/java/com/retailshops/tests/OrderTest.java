package com.retailshops.tests;

import com.retailshops.offermarket.model.Log;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	/** JPA entity manager. */
	private EntityManager em;
        
	@Before public void setUp() {
		em = Persistence.createEntityManagerFactory("offermanager").createEntityManager();
	}
	
	@Test public void insert_to_mogodb() {
		// given
		Log a = new Log();
		a.setDescription("this is testing ");
                a.setCode("101");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
                em.persist(a);
		em.flush();
		tx.commit();
		
		
	}
}
