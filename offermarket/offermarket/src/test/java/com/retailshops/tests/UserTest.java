/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.tests;




import com.retailshops.offermarket.dao.UserDAO;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import java.util.ArrayList;
import java.util.List;

//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.FullTextQuery;
//import org.hibernate.search.jpa.Search;
//import org.hibernate.search.query.DatabaseRetrievalMethod;
//import org.hibernate.search.query.ObjectLookupMethod;
//import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
/**
 *
 * @author sarathraj
 */
public class UserTest {
    
    public UserTest() {
    }
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    @BeforeClass
    public static  void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emf=Persistence.createEntityManagerFactory("offermanager");
        entityManager = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test()
    {

    }
    
    public void testdata()
    {
       
            
           //return q.getResultList();
             Query q= entityManager.createQuery( "from User h where email = :e" , User.class );
             q.setParameter("e","sarathraj99@gmail.com");
               User users = (User)q.getSingleResult();
              
            
            System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
         
    }
    
    
  
    public void insert_to_mogodb() {
		// given
		User a = new User();
		a.setEmail("sarathraj99@gmail.com");
                a.setPassword("333333");
                
                
                
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(a);
		entityManager.flush();
		tx.commit();
		
		
	}
   
    public void update_to_mogodb_transiant_object () {
       
             UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
       User a= u.userByemail("sarathraj99@gmail.com");
        User b= a;
        
        // emf=Persistence.createEntityManagerFactory("offermanager");
      // entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
           
    Outlet l = new Outlet();
              //  l.setName("asad");
             //   l.setType("retail");
		
                 Outlet l1 = new Outlet();
              ///  l1.setName("sadsadsad");
              //  l1.setType("sadasdasd");
                
                List<Outlet> e = new ArrayList<Outlet>();
                e.add(l);
                e.add(l1);
               b.setOutlets(e);
                entityManager.merge(b);
		entityManager.flush();
		tx.commit();
    }
    
}
