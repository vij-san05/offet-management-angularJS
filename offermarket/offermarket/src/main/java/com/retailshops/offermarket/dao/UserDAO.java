/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.dao;



import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.dao.exceptions.NonexistentEntityException;
import com.retailshops.offermarket.dao.exceptions.RollbackFailureException;
import com.retailshops.offermarket.model.FinalPoints;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.Point;
import com.retailshops.offermarket.model.Pos;
import com.retailshops.offermarket.model.Product;
import com.retailshops.offermarket.model.PublicData;
import com.retailshops.offermarket.model.RetailsCounts;
import com.retailshops.offermarket.model.TransactionsLog;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import com.retailshops.offermarket.util.GenerateUUID;
import com.retailshops.offermarket.util.ServerURL;
//import com.sun.istack.internal.FinalArrayList;
//import com.sun.javafx.scene.control.skin.VirtualFlow;
//import com.sun.jmx.snmp.BerDecoder;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import org.bson.types.ObjectId;

//org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.search.query.dsl.QueryBuilder;
/**
 *
 * @author Vijay
 */
public class UserDAO implements Serializable {

     
    public UserDAO(EntityManagerFactory emf) {
       
        this.emf = emf;
    }
   
    private EntityManagerFactory emf = null;
    private  EntityTransaction tx =null;
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    User publicusr;
    public User createpublicUser(String email)
    {
        publicusr = new User();
        publicusr.setEmail(email);
        publicusr.setLastUpdatedTime(new Date().toString());
        publicusr.setDeductionscore(0);
        publicusr.setRole(3);
        publicusr.setStatus(1);
        publicusr.setPassword("");
        FinalPoints p= new FinalPoints();
        p.setOutletId("000000000000");
        p.setTotalpoints("0");
        
        List<FinalPoints> data = new ArrayList<FinalPoints>();
        data.add(p);
        publicusr.setUsertotalPointsInOutlet(data);
             EntityManager em = null;
        try {
           // utx.begin();
            em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
                em.persist(publicusr);
                tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
     
        return publicusr;
    }
    
    
    public void create(User user) throws RollbackFailureException, Exception {
       EntityManager em = null;
        try {
           // utx.begin();
            em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
                em.persist(user);
                tx.commit();
         //   utx.commit();
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

    public void edit(User user) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
          em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
            user = em.merge(user);
           tx.commit();
        } catch (Exception ex) {
            try {
            tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjectId id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            em.remove(user);
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

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(User.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
            List<User> logs = em
            .createQuery( "from Log" , User.class )
            .getResultList();
            return logs;
        } finally {
            em.close();
        }
    }

    public User findUser(ObjectId id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
  
    User user;
  public int findUserByEmail(String username)
    {
        EntityManager em = getEntityManager();
        try {

          // User logs = em.createQuery("from User h where h.email = :email and h.password = :passwrd" , User.class )
                  //   set
              Query q= em.createQuery( "from User h where email = :e" , User.class );
             q.setParameter("e",username);
                 
                 try{
                      System.out.println("######>>>>" + username);
                      user=(User) q.getSingleResult();
                    System.out.print("######"+ user.getEmail() + "######" + user.getPassword() + "#####");
                    } catch (NoResultException nre) {
                      return 0; 
                    } 
             System.out.println("returning true"); 
             return 1; 
                      
                
        } finally {
            em.close();
        }
        
    }
  
  User users;
  public User login(String username , String password)
  {
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e and password =:p " , User.class );
             q.setParameter("e",username);
             q.setParameter("p",password);
             
               users = (User)q.getSingleResult();
        } catch (NoResultException nre) {
                      return null; 
                    } 
             System.out.println("returning true"); 
            return users;   
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }
  
   User users1;
  public User userByemail(String email )
  {
      System.out.println(">>>>>>>"+email);
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",email);
        
             
            users1 = (User)q.getSingleResult();
        } catch (NoResultException nre) {
                      return null; 
                    }
       finally
       {
          //emf.close();
       }
             System.out.println("returning true"); 
            return users1;   
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }
    
  
  ///////////////////////////outlet action //////////////////////////////////////////////////////////////////
  
  Outlet toremove;
  List<Outlet> outlets;
  List<Product> productsss;
  boolean statusdeelte;
  int statusid,statusdat;
   public List<Outlet> _outlet_action(Outlet outletinput , String email,String action) {
       statusdeelte=false;
       statusid=0;
       statusdat=0;
         EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
        boolean mergestatus =false;
        
        
            
        EntityTransaction tx = em.getTransaction();
		          tx.begin();
                          
    try{                                            
         outlets = actualdata.getOutlets();  
         productsss=actualdata.getProducts();
        if(action.matches("delete"))
        {
            System.out.println(">>>>>>ddddddddddddddddddd");
            
                               for(Outlet o : outlets)
                               {
                                   if(o.getOutletid().matches(outletinput.getOutletid()))
                                   {
                                       
                                   if(productsss!=null)
                                   {
                                       for(Product poid: productsss)
                                       {
                                            if (poid.getOutletid().matches(o.getOutletid()))
                                            {
                                               // statusid=
                                                statusdeelte =true;//cant delete ;
                                            }
                                       }
                                   }
                                   
                                   
                                      
                                               statusdat=statusid;
                                               System.out.println("statusdat = "+ statusdat);
                                               //o.setHibbenstatus("notactive");
                                   }
                                   
                                    statusid=statusid+1;
                                   
                                   
                                  
                               }
                               if(statusdeelte==false)
                                outlets.remove(statusdat);
                               System.out.println("statusdat ddd = "+ statusdeelte);
                              // outlets.remove(outletinput);
                             //  outlets.add(outletinput); 
                               actualdata.setOutlets(outlets);
                               em.merge(actualdata);
	                       em.flush();
		               tx.commit();
        }
        else if(action.matches("add"))
        {
            //check if the outlet is there in db then merge
            System.out.println("stage 1" );
            if(!outlets.isEmpty() || outlets.size()>0)
            {  System.out.println("stage 11  "+outlets.get(0).getCity());
                //data is there
                //check data and update 
                for(Outlet o :outlets)
                { System.out.println("stage 1112" + o.getOutletid()+ " " + outletinput.getOutletid());
                   if(o.getOutletid().matches(outletinput.getOutletid()))
                           {
                              //matches and merge update
                               //outlets.add(outletinput);
                               toremove=o;
                               mergestatus =true; 
                           }
                      
                 }                         
            }
            else if(outlets.isEmpty() )
            {
                   outlets = new ArrayList<Outlet>();
                   System.out.println("stage 2" + actualdata.getEmail() + " " + outletinput.getCountry()); 
                   outlets.add(outletinput);
                   actualdata.setOutlets(outlets);
                   em.merge(actualdata);
	           em.flush();
		   tx.commit();
                    //insert
            }
             if( mergestatus==true  )
            {
                              outlets.remove(toremove);
                              outlets.add(outletinput); 
                              actualdata.setOutlets(outlets);
                              em.merge(actualdata);
	                      em.flush();
		              tx.commit();
            }
             else if(mergestatus==false)
             {
                              outlets.add(outletinput); 
                              actualdata.setOutlets(outlets);
                              em.merge(actualdata);
	                      em.flush();
		              tx.commit();
             }
        }
        }catch (Exception e)
        {e.printStackTrace();
        }
        //check if the outlet is there in db then merge
         return  outlets;                
       //check if not then add 
    }
   
   
    public List<Outlet> getOutlets(String  email)
    {
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
            // outlets = new ArrayList<Outlet>();
           outlets =   outlets = actualdata.getOutlets();
           List<Outlet> finaldata = new ArrayList<Outlet>();
           for (Outlet o : outlets)
           {
               String tempdata = o.getLogo();
               o.setLogo(ServerURL.dataURL+tempdata);
               finaldata.add(o);
           }
           
             return finaldata;
    
    }
    
    
    
    
    public boolean useroutletcheck( Outlet o ,String  email)
    {
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
            // outlets = new ArrayList<Outlet>();
             outlets = actualdata.getOutlets();
             
             for(Outlet op:outlets)
             {
                 if(op.getOutletid().matches(o.getOutletid()))
                 {
                     return true;
                 }
             }
             return false;
    
    }
    
  
   ///////////////////////////////////product action //////////////////////////////////////////////////////////////////
   
     Product toremoveprod;
  List<Product> products;
  boolean statusdeelteproduct;
    public boolean userproductcheck( Product o ,String  email)
    {
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        
            System.out.println(">>>" + email);
             User userdata= userByemail(email);
             User actualdata= userdata;
            // outlets = new ArrayList<Outlet>();
             products = actualdata.getProducts();
             
             for(Product op:products)
             {
                 System.out.println("#"+ op.getProductid() + "### "+ o.getProductid() );
                 if(op.getProductid().matches(o.getProductid()))
                 {
                     return true;
                 }
             }
             return false;
    
    }
    
    
    
    
    
   
   public List<Product> _product_action(Product producttinput , String email,String action) {
       statusdeelteproduct =false;
       
         EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
        boolean mergestatus =false;
        
        
            
        EntityTransaction tx = em.getTransaction();
		          tx.begin();
                          
    try{                                            
         products = actualdata.getProducts();         
        if(action.matches("delete"))
        {
            
                                statusid=0;
                                statusdat=0;
                               for(Product o : products)
                               {   
                                   if(o.getProductid().matches(producttinput.getProductid()))
                                   {           // statusid=
                                               statusdeelteproduct =true;//cant delete ;
                                               
                                               statusdat=statusid;
                                              // o.getClass()
                                               System.out.println("statusdat = "+ statusdat);
                                               //o.setHibbenstatus("notactive");
                                   }
                                   
                                   statusid=statusid+1;
                               }
                               if(statusdeelteproduct==true)
                                products.remove(statusdat);
            
            
                               //products.remove(producttinput);
                             //  outlets.add(outletinput); 
                               actualdata.setProducts(products);
                               em.merge(actualdata);
	                       em.flush();
		               tx.commit();
        }
        else if(action.matches("add"))
        {
            //check if the outlet is there in db then merge
            System.out.println("stage 1" );
            if(!products.isEmpty() || products.size()>0)
            {  System.out.println("stage 11  "+products.get(0).getOffername());
                //data is there
                //check data and update 
                for(Product o :products)
                { System.out.println("stage 1112" + o.getProductid()+ " " + producttinput.getProductid());
                   if(o.getProductid().matches(producttinput.getProductid()))
                           {
                              //matches and merge update
                               //outlets.add(outletinput);
                               toremoveprod=o;
                               mergestatus =true; 
                           }
                      
                 }                         
            }
            else if(products.isEmpty() )
            {
                   products = new ArrayList<Product>();
                   System.out.println("stage 2" + actualdata.getEmail() + " " + producttinput.getProductid()); 
                   products.add(producttinput);
                   actualdata.setProducts(products);
                   em.merge(actualdata);
	           em.flush();
		   tx.commit();
                    //insert
            }
             if( mergestatus==true  )
            {
                              products.remove(toremoveprod);
                              products.add(producttinput); 
                              actualdata.setProducts(products);
                              em.merge(actualdata);
	                      em.flush();
		              tx.commit();
            }
             else if(mergestatus==false)
             {
                 
                              products.add(producttinput); 
                              actualdata.setProducts(products);
                              em.merge(actualdata);
	                      em.flush();
		              tx.commit();
             }
        }
        }catch (Exception e)
        {e.printStackTrace();
        }
        //check if the outlet is there in db then merge
         return  products;                
       //check if not then add 
    }
   
   
    public List<Product> getProducts(String  email)
    {
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
            // outlets = new ArrayList<Outlet>();
             products = actualdata.getProducts();
             
               List<Product> finaldata = new ArrayList<Product>();
           for (Product o : products)
           {
               String tempdata = o.getProductimagea();
               o.setProductimagea(ServerURL.dataURL+tempdata);
               finaldata.add(o);
           }
             
             
             
             return finaldata;
    
    }
    
    ///////////////////////////////////////////////pos data ////////////////////////////////////////////////////////////////////
    
    User userspos;
    Double usrpoints=0.0;
  public String userByemailandPosToPoints(String email,String outlet )
  {
      usrpoints =0.0;
      System.out.println(">>>>>>>"+email);
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",email);
            userspos = (User)q.getSingleResult();
            
          List<FinalPoints> points=  userspos.getUsertotalPointsInOutlet();
            
          if(points == null)
          {
              return null;
          }
          else
          {
              
              for (FinalPoints p : points)
              {
                  if(p.getOutletId().matches(outlet))
                  { System.out.print("User points martching outlet ");
                   usrpoints= Double.parseDouble(p.getTotalpoints());
                  }
                  
              }
          
            System.out.print("User points >>> " + usrpoints);  
         // return String.valueOf(usrpoints-userspos.getDeductionscore());
            return String.valueOf(usrpoints);
          }
            
            
            
            
            
        } catch (NoResultException nre) {
                      return null; 
                    }
       finally
       {
          //emf.close();
       }
              
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }
  
  
  
  //get product details by eachoutlet
  User retailuserdata;
  List<Product> listproducts;
 public List<Product>  viewproductsbyOutlet(String outlet,String retailemail)
 { 
     listproducts= new ArrayList<Product>();
      try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",retailemail);
             retailuserdata = (User)q.getSingleResult();
            
          List<Product> products=  retailuserdata.getProducts();
            
          if(products == null)
          {
               System.out.print("###### null products");
              return null;
          }
          else
          {
              
              for (Product p : products)
              {
                  if(p.getOutletid().matches(outlet.trim()))
                  {
                     System.out.print("######  products>>>" +p.getProductname() );  
                    listproducts.add(p);
                  }
                  
              }
              
              

             return listproducts; 
          }
            
            
            
            
            
        } catch (NoResultException nre) {
                      return null; 
                    }
       finally
       {
          //emf.close();
       }
     
     
    
 }
 
 
 //pos operations calculation
 Double totalprice =0.0;
 Double pointsacquired=0.0;
 //Double deducionpoints =0.0;
 public Pos getCalcualtedResult(Pos data,String email)
 {
     // get product and price 
      
     
     
     List<Product> products =  getProducts(email);
     for (Product p : products)
     {
         if(data.getProductid().matches(p.getProductid()))
                 {
                    //  User u =userByemail(data.getEmail());
                    //if(u!=null) 
                    //deducionpoints=pointsacquired=Double.valueOf(u.getDeductionscore());
                     totalprice= Double.valueOf(p.getProductprice() * data.getQuantity());
                     data.setTotalprice(totalprice);
                     pointsacquired=(Double.valueOf(p.getUserearningpoints()) * data.getQuantity());
                     
                     data.setPointacquired(pointsacquired.intValue());
                 }
     }
     
     //get points for deduction 
     
     
     
//     List<Outlet> outlets = getOutlets(email);
//     
//       for (Outlet o : outlets)
//     {
//         if(data.getOutletid().matches(o.getOutletid()))
//                 {
//                     
//                     
//                 }   
     
// }
    return data;  
}
 
 User addpointsuser;
 Double usrpointsdata=0.0;
public Pos addUserPoints(Pos data,String email)
{
    usrpointsdata=0.0;
    
    
    try{
    EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",data.getEmail());
             try{
             addpointsuser = (User)q.getSingleResult();
             }
             catch(Exception e)
             {
                 //e.printStackTrace();
                 System.out.println("no user");
                 //so to create a return user 
               addpointsuser= createpublicUser(data.getEmail());
             }
             
             
             //getoutlet by email 
               List<Outlet> outlets = getOutlets(email);
     
               for (Outlet o : outlets)
              {
               if(data.getOutletid().matches(o.getOutletid()))
                 {
                     outletnameforpublic=o.getNameoutlet();
                     outletcityforpublic=o.getCity();
                 }
              }
             
            
             Point p = new Point();
             p.setOutletid(data.getOutletid());
             p.setProductId(data.getProductid());
             p.setStartprice(data.getTotalprice());
             p.setUserpoints(Double.parseDouble(String.valueOf(data.getPointacquired())));
             p.setFinalprice(data.getTotalprice());
           
               // need to save this point  
              //  p.get
             
             List<Point> sumpoints=  addpointsuser.getRetailpoints();
             if(sumpoints.isEmpty() || sumpoints==null)
             {
                 System.out.println("first time" +addpointsuser.getEmail() );
                 sumpoints = new ArrayList<Point>();
                // usrpointsdata= Double.parseDouble(String.valueOf(data.getPointacquired()));
             } 
            
             
             
             
           addpointsuser.getRetailpoints().add(p);
         List<FinalPoints>  fianlpoints=  addpointsuser.getUsertotalPointsInOutlet();
                //  sumpoints.add(p);
         if(fianlpoints.isEmpty() || fianlpoints==null)
         {
             System.out.println("empy data time");
             fianlpoints =new ArrayList<FinalPoints>();
             FinalPoints datafinal = new FinalPoints();
             datafinal.setOutletId(data.getOutletid().trim());
              datafinal.setOutletname(outletnameforpublic);
             datafinal.setLocation(outletcityforpublic);
             datafinal.setTotalpoints(String.valueOf(data.getPointacquired()));
             addpointsuser.setUsertotalPointsInOutlet(fianlpoints);
              data.setUsrPoints(Double.toString(data.getPointacquired()));
         }
         else
         {
             System.out.println("not empty data time");
	    int index=0;
            boolean statusindes=false;
            int statusindex=0;
              for (FinalPoints pa : fianlpoints)
              {
                   System.out.println(pa.getOutletId() + "ddd "+data.getOutletid());
                  if(pa.getOutletId().trim().matches(data.getOutletid().trim()))
                  {
                      System.out.println("ddd"+usrpointsdata);
                      System.out.println("ddd  LL "+pa.getTotalpoints());
                      //here disply after deduction
                      
                   usrpointsdata= Double.parseDouble(String.valueOf(data.getPointacquired() + (int)(Double.parseDouble(pa.getTotalpoints())))); 
                   
                     System.out.println("usrpointsdata added ##"+ usrpointsdata);
                   statusindex=index;
                   statusindes=true;
                  }
                  
                  index=index+1;
              }
              
                 System.out.println("statusindex" +statusindex );
              //delete item from the index 
                 if(statusindes==true)
                  fianlpoints.remove(statusindex);
                 else
                 {
                     usrpointsdata=Double.valueOf(String.valueOf(data.getPointacquired()));
                 }
              
              // add item 
                 
                     
              
              FinalPoints datafinal = new FinalPoints();
             datafinal.setOutletId(data.getOutletid().trim());
             datafinal.setTotalpoints(String.valueOf(usrpointsdata));
              datafinal.setOutletname(outletnameforpublic);
             datafinal.setLocation(outletcityforpublic);
             fianlpoints.add(datafinal);
             addpointsuser.setUsertotalPointsInOutlet(fianlpoints);
              data.setUsrPoints(Double.toString(usrpointsdata));
         }    
//              usrpointsdata =usrpointsdata- addpointsuser.getDeductionscore();
//              if(usrpointsdata<=0)
//              {
//                   System.out.println("usrpointsdata deduction part" + usrpointsdata);
//                  usrpointsdata =usrpointsdata;
//              }
             
              
              try{
              
              //EntityManager em1 = getEntityManager();
               EntityTransaction tx2 = em.getTransaction();
               tx2.begin();
               System.out.println("transactions  ##"+ addpointsuser.getEmail());
                 addpointsuser.setRetailpoints(sumpoints);
                 System.out.println("transaction area");              
                 em.merge(addpointsuser); 
	         em.flush();
		 tx2.commit();
                 System.out.println("transaction finished");       
              }catch(Exception e)
              {
                  e.printStackTrace();
              }
              //add to sales table
                 EntityManager em3 = getEntityManager();
                 EntityTransaction tx1 = em3.getTransaction();
	         tx1.begin();
                TransactionsLog obj = new TransactionsLog();
                obj.setOutletid(p.getOutletid());
                obj.setProductid(p.getProductId());
                obj.setPointacquired(Double.valueOf(data.getPointacquired()));
                obj.setQuantity(data.getQuantity());
                obj.setEmail(data.getEmail());
                obj.setTotalprice(data.getTotalprice());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss") ;
                Date today = Calendar.getInstance().getTime();        
                String reportDate = df.format(today);
                   //lastUpdatedTime=reportDate;
                obj.setDateandtime(reportDate);
                em3.merge(obj);
	        em3.flush();
                tx1.commit();
    
    return data;  
    }catch(Exception e)
    {
        e.printStackTrace();
    }
     return data;
}

User deductionsample;
Double datadeduction;
public Pos deductCalculate(Pos data,String email)
{
    // $scope.pos.finalprice=  $scope.pos.totalprice - ($scope.outlet.priceperpoint*$scope.pos.pointsdeducted) 
    
     try{
    EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",data.getEmail());
             deductionsample = (User)q.getSingleResult();
             
             if(deductionsample==null)
             {
                 data.setFinalprice(0.0);
                 return data;
             }
             
             
              List<Outlet> outlets = getOutlets(email);
     
               for (Outlet o : outlets)
              {
               if(data.getOutletid().matches(o.getOutletid()))
                 {
                       
                    datadeduction= data.getTotalprice() - (o.getPriceperpoint()* data.getPointsdeducted());
                     //totalpricebeforededuction=Double.valueOf(data.getTotalprice());
                     
                     //pricetodeduct= o.getPriceperpoint() * Double.valueOf(data.getPointsdeducted());
                     
                    // totalpricebeforededuction = totalpricebeforededuction-pricetodeduct;
                    // data.setFinalprice(totalpricebeforededuction);
                    // deletepointsuser.setDeductionscore(deletepointsuser.getDeductionscore()+data.getPointsdeducted());
                     
                 }   
     
               } 
               if ((Double.valueOf(data.getUsrPoints().replace("\"","")))>=data.getPointsdeducted())
               data.setFinalprice(datadeduction);
               else
                   data.setFinalprice(0.0);
               return data;
             
             
             
     }catch(Exception e)
     {
         e.printStackTrace();
     }
    
    
    
    return null;
}



User deletepointsuser;
Double totalpricebeforededuction = 0.0;
Double pricetodeduct=0.0;
String outletnameforpublic;
String outletcityforpublic;
public Pos deletePoints(Pos data,String email)
{
    try{
    EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",data.getEmail());
             deletepointsuser = (User)q.getSingleResult();
             
            
             // deducted price
               List<Outlet> outlets = getOutlets(email);
     
               for (Outlet o : outlets)
              {
               if(data.getOutletid().matches(o.getOutletid()))
                 {
                     totalpricebeforededuction=Double.valueOf(data.getTotalprice());
                     
                     pricetodeduct= o.getPriceperpoint() * Double.valueOf(data.getPointsdeducted());
                     
                     totalpricebeforededuction = totalpricebeforededuction-pricetodeduct;
                     data.setFinalprice(totalpricebeforededuction);
                     outletnameforpublic=o.getNameoutlet();
                     outletcityforpublic=o.getCity();
                     //deletepointsuser.setDeductionscore(deletepointsuser.getDeductionscore()+data.getPointsdeducted());
                 }   
     
               }
             
             
             //dedeuct pints from db 
             ////////////////user points to display
               usrpointsdata=0.0;
//                List<Point> sumpoints=  deletepointsuser.getRetailpoints();
//             if(sumpoints.isEmpty())
//             {
//                 sumpoints = new ArrayList<Point>();
//                 usrpointsdata= 0.0;
//                 //data.setUsrPoints(usrpointsdata);
//             }      
             List<FinalPoints>  fianlpoints=  deletepointsuser.getUsertotalPointsInOutlet();
             
         if(fianlpoints.isEmpty() || fianlpoints==null)
         {
             System.out.println("empy data time");
              data.setFinalprice(0.0);
              return data;
         }
         else
         {
             System.out.println("not empty data time");
	    int index=0;
            boolean statusindes=false;
            int statusindex=0;
              for (FinalPoints pa : fianlpoints)
              {
                   System.out.println(pa.getOutletId() + "ddd "+data.getOutletid());
                  if(pa.getOutletId().trim().matches(data.getOutletid().trim()))
                  {
                      System.out.println("ddd"+usrpointsdata);
                      System.out.println("ddd  LL "+pa.getTotalpoints());
                      //here disply after deduction
                      
                   usrpointsdata= Double.valueOf( String.valueOf((int)(Double.parseDouble(pa.getTotalpoints())) - data.getPointsdeducted())); 
                   
                     System.out.println("usrpointsdata added ##"+ usrpointsdata);
                   statusindex=index;
                   statusindes=true;
                  }
                  
                  index=index+1;
              }
              
              
               if(statusindes==true)
                  fianlpoints.remove(statusindex);
               
              FinalPoints datafinal = new FinalPoints();
             datafinal.setOutletId(data.getOutletid().trim());
             datafinal.setOutletname(outletnameforpublic);
             datafinal.setLocation(outletcityforpublic);
             datafinal.setTotalpoints(String.valueOf(usrpointsdata));
             data.setUsrPoints(String.valueOf(usrpointsdata));
             fianlpoints.add(datafinal);
             deletepointsuser.setUsertotalPointsInOutlet(fianlpoints);
               
               
               
         }
              
               
               
             
             
           
	   
//              for (Point pa : sumpoints)
//              {
//                   System.out.println(pa.getOutletid() + "ddd "+data.getOutletid());
//                  if(pa.getOutletid().trim().matches(data.getOutletid().trim()))
//                  {
//                    
//                   usrpointsdata= usrpointsdata + pa.getUserpoints(); 
//                   
//                     System.out.println("usrpointsdata added ##"+ usrpointsdata);
//                   
//                  }
//                  
//              }
//              
//              usrpointsdata =usrpointsdata- deletepointsuser.getDeductionscore();
//              if(usrpointsdata<=0.0)
//                  usrpointsdata =usrpointsdata;
//              data.setUsrPoints(Double.toString(usrpointsdata));
               
               
               
           
             
             
            ///////////////////////////end here /////////////////////
             EntityTransaction tx = em.getTransaction();
	     tx.begin();
                 em.merge(deletepointsuser);
	         em.flush();
		 tx.commit();
              // add to sales tables 
              
             EntityTransaction tx1 = em.getTransaction();
	     tx1.begin();
              
                TransactionsLog obj = new TransactionsLog();
                obj.setOutletid(data.getOutletid());
                obj.setProductid(data.getProductid());
                obj.setPointsdeducted(deletepointsuser.getDeductionscore());
                obj.setQuantity(data.getQuantity());
                obj.setEmail(data.getEmail());
                obj.setTotalprice(data.getTotalprice());
                obj.setFinalprice(data.getFinalprice());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss") ;
                    Date today = Calendar.getInstance().getTime();        
                   String reportDate = df.format(today);
                   //lastUpdatedTime=reportDate;
                obj.setDateandtime(reportDate);
             
             em.merge(obj);
	     em.flush();
             tx1.commit();
             
            
    
    return data;  
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    return null;
}


 List<TransactionsLog> listtrans;
  public List<TransactionsLog> TransactionsLogData(String outlet)
  {
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from TransactionsLog h where outletid = :e " , TransactionsLog.class );
             q.setParameter("e",outlet);
             
             
               listtrans = (List<TransactionsLog>)q.getResultList();
        } catch (NoResultException nre) {
                      return null; 
                    } 
             System.out.println("returning true"); 
            return listtrans;   
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }


   List<Outlet> outletsTrans;
    public List<TransactionsLog> getsalesShops(String  email)
    {
        List<TransactionsLog> listtransdata = new ArrayList<TransactionsLog>();
        List<TransactionsLog> listtr = new ArrayList<TransactionsLog>();
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             User userdata= userByemail(email);
             User actualdata= userdata;
            // outlets = new ArrayList<Outlet>();
             outletsTrans = actualdata.getOutlets();
                   
             if(outletsTrans!=null)
             {
                 for(Outlet o :outletsTrans )
                 {
                     
                    listtransdata=TransactionsLogData(o.getOutletid());
                    listtr.addAll(listtransdata);
                     
                     
                 }
             }
             
             return listtr;
    
    }
    
    
   public int  updateUser(User user)
   {
       try{
        EntityManager em = getEntityManager();
          EntityTransaction tx1 = em.getTransaction();
	     tx1.begin();
               em.merge(user);
	     em.flush();
             tx1.commit();
             return 1;
       }catch (Exception e)
       {
            e.printStackTrace();
           return 0;
    
       }
             
   }
   
   
   RetailsCounts counts;
   int a,b,c,d;
   List<User> userssample ;
    List<Product> productlist;
   public RetailsCounts  getMajorCounts(String email)
   {   userssample  = new ArrayList<User>();
       counts= new RetailsCounts();
       
       
       //outletcounts
       
          try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e " , User.class );
             q.setParameter("e",email);
             retailuserdata = (User)q.getSingleResult();
            
           a=  retailuserdata.getOutlets().size();
          if(a!=0)
          {
              counts.setOutletscount(String.valueOf(a));
          }
          else
          {
              counts.setOutletscount("0");
          }
          
           b=  getsalesShops(email).size();
           if(b!=0)
          {
              counts.setSalescount(String.valueOf(b));
          }
          else
          {
              counts.setSalescount("0");
          }

           //product count 
           c = retailuserdata.getProducts().size();
            if(c!=0)
          {
              counts.setUserscount(String.valueOf(c));
          }
          else
          {
              counts.setUserscount("0");
          }
           
           //offercount
          d=0;
           productlist= retailuserdata.getProducts();
                for(Product p : productlist)
                {
                    if(p.getOfferstatus().matches("active"))
                    {
                     d= d+1;   
                    }
                        
                }
                if(d!=0)
          {
              counts.setOffercount(String.valueOf(d));
          }
          else
          {
              counts.setOffercount("0");
          } 
              
                   
                   
            
          
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
       
       
   
       
       
       
       return counts;
       
   }
    List<User> allusers;
    public List<User>  getAllretailUsers()
   {
       try{
        EntityManager em = getEntityManager();
          Query q= em.createQuery( "from User h where status = :e and role =:a" , User.class );
             q.setParameter("e",1);
             q.setParameter("a",2);
                 
                 try{
                      //System.out.println("######>>>>" + username);
                      allusers=( List<User>) q.getResultList();
                      return allusers;
                    //System.out.print("######"+ user.getEmail() + "######" + user.getPassword() + "#####");
                    } catch (NoResultException nre) {
                      return null; 
                    } 
       }catch (Exception e)
       {
            e.printStackTrace();
          
    
       }
             return allusers;
   }
   
   
    ArrayList<PublicData> pdata;
  public ArrayList<PublicData>  getMajorPublicData() 
  {
      pdata = new ArrayList<PublicData>();
      List<User> users = getAllretailUsers();
      List <Outlet> outlet;
      List <Product> proList;
      System.out.print("entering a");
      for(User u : users)
      {System.out.print("entering b");
          outlet=u.getOutlets();
          if(outlet!=null)
          { System.out.print("entering c");
              for(Outlet out : outlet)
              { System.out.print("entering d");
                  if(out.getStatus().matches("active") && u.getProducts()!=null)
                  {
                   System.out.print("entering e");
                   
                   
                  proList = u.getProducts();
                  
                  
                  for(Product pro : proList)
                  {
                      if(pro.getProductstatus().matches("active") && pro.getOfferstatus().matches("active"))
                      {PublicData p = new PublicData();
                       p.setOutletname(out.getNameoutlet());
                       p.setOutletcity(out.getCity());
                       p.setOutletaddress(out.getStreet() +" ," + out.getCountry() );
                      p.setOutletlatitide(out.getLatitude());
                      p.setOutletlogitude(out.getLogitude());
                      p.setOutletemail(u.getEmail());
                          
                          System.out.print(">>>>>" + pro.getProductname());
                          p.setProductname(pro.getProductname());
                          p.setProductimage(ServerURL.dataURL+pro.getProductimagea());
                          p.setProductdetails(pro.getProductdescription());
                          
                          p.setProductoffer(pro.getOffername());
                          p.setProductofferdetails(pro.getOfferdescription());
                          
                          p.setRewarddetails(pro.getRewardsdetails());
                          p.setRewardpoints(String.valueOf(pro.getRewardpoints()));
                          
                          p.setPointsyoucanearnperproduct(String.valueOf(pro.getUserearningpoints()));
                          
                          p.setProductprice(String.valueOf(pro.getProductprice()));
                          
                          
                          PublicData k = p;
                           pdata.add(k);
                         // pdata.notify();
//                        
//                          p.setProductname("");
//                          p.setProductimage("");
//                          p.setProductdetails("");
//                          
//                          p.setProductoffer("");
//                          p.setProductofferdetails("");
//                          
//                          p.setRewarddetails("");
//                          p.setRewardpoints("");
//                          
//                          p.setPointsyoucanearnperproduct("");
//                          
//                          p.setProductprice("");
                      }
                  }
                   
                  }
              }
          }
      }
      return pdata;
  }
   
   //////////////get all users /////////////////////////////////////
    List<User> allusersforpermission;
    public List<User>  getAllUserss()
   {
       try{
        EntityManager em = getEntityManager();
          Query q= em.createQuery( "from User where role =:a" , User.class );
            q.setParameter("a",2);
                 
                 try{
                      //System.out.println("######>>>>" + username);
                      allusersforpermission=( List<User>) q.getResultList();
                      System.out.println("user listting "+ allusersforpermission.size());
                      return allusersforpermission;
                    //System.out.print("######"+ user.getEmail() + "######" + user.getPassword() + "#####");
                    } catch (NoResultException nre) {
                      return null; 
                    } 
       }catch (Exception e)
       {
            e.printStackTrace();
          
    
       }
             return allusersforpermission;
   }
    
    
    
    public List<User>  getAllUsers()
   {
       try{
        EntityManager em = getEntityManager();
          Query q= em.createQuery( "from User " , User.class );
           // q.setParameter("a",2);
                 
                 try{
                      //System.out.println("######>>>>" + username);
                      allusersforpermission=( List<User>) q.getResultList();
                      System.out.println("user listting "+ allusersforpermission.size());
                      return allusersforpermission;
                    //System.out.print("######"+ user.getEmail() + "######" + user.getPassword() + "#####");
                    } catch (NoResultException nre) {
                      return null; 
                    } 
       }catch (Exception e)
       {
            e.printStackTrace();
          
    
       }
             return allusersforpermission;
   }
    
    public User edituserpermissions(User u)
    {
          EntityManager em = getEntityManager();
      User user = userByemail(u.getEmail());
      
      if (user.getStatus()==1)
      {
          user.setStatus(0);
      }
      else
      {
          user.setStatus(1);
      }
        try{
        
          EntityTransaction tx1 = em.getTransaction();
	     tx1.begin();
               em.merge(user);
	     em.flush();
             tx1.commit();
             return user;
       }catch (Exception e)
       {
            e.printStackTrace();
           return user;
    
       }
      
      
    }
    
    
    ///////////get all transactions ////////////////////////////////////////////////////////////////////////
    
     List<Outlet> outletsTransd;
    public List<TransactionsLog> salesshopsdetails(String  email)
    {
        List<TransactionsLog> listtransdata = new ArrayList<TransactionsLog>();
        List<TransactionsLog> listtr = new ArrayList<TransactionsLog>();
        // EntityManager em = getEntityManager();
            // UserDAO u = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
             
        
        
            
            List<User> userdata= getAllUserss();
            
            
           
            
             for(User actualdata :userdata)
             {
             // outlets = new ArrayList<Outlet>();
             outletsTransd = actualdata.getOutlets();
                   
             if(outletsTransd!=null)
             {
                 for(Outlet o :outletsTransd )
                 {
                     
                    listtransdata=TransactionsLogData(o.getOutletid());
                    listtr.addAll(listtransdata);
                     
                     
                 }
             }
             }
             
             
             
             
             return listtr;
    
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////
    
                  //////////////////Mobile part ///////////////////////////////
     User userslogin;
  public User userloginmob(User u)
  {
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e and password =:p " , User.class );
             q.setParameter("e",u.getEmail());
             q.setParameter("p",u.getPassword());
             
               users = (User)q.getSingleResult();
               
               if(users!=null)
               {
                  String mobilekey = GenerateUUID.getUUID();
                  
                  users.setMobilekey(mobilekey);
                  
                   
               EntityTransaction tx1 = em.getTransaction();
	       tx1.begin();
               em.merge(users);
	       em.flush();
               tx1.commit();
                  
               }
               
        } catch (NoResultException nre) {
                      return null; 
                    }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
             System.out.println("returning true"); 
            return users;   
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }
    
  
  //status checkk
  
      User statuscheck;
  public User usercheckmob(User u)
  {
       try {
      EntityManager em = getEntityManager();
       Query q= em.createQuery( "from User h where email = :e  " , User.class );
             q.setParameter("e",u.getEmail());
            
             
               users = (User)q.getSingleResult();
               
               if(users!=null)
               {
                  return users;
                  
               }
               
        } catch (NoResultException nre) {
                      return null; 
                    }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
             System.out.println("returning true"); 
            return users;   
              
            
           // System.out.print("######"+ users.getEmail() + "######" + users.getPassword() + "#####");
  }
  /////////////registration////////////////////
  
  
  
          
            User regcheck;
  public User registrationmobiledata(User u)
  {
       regcheck=u;
       regcheck.setRole(3);
       regcheck.setDeductionscore(0);
       regcheck.setStatus(1);
       regcheck.setMobilekey(GenerateUUID.getUUID());
       
      
        FinalPoints p= new FinalPoints();
        p.setOutletId("000000000000");
        p.setTotalpoints("0");
        
        List<FinalPoints> data = new ArrayList<FinalPoints>();
        data.add(p);
        regcheck.setUsertotalPointsInOutlet(data);
       
       
       
       
      EntityManager em = getEntityManager();
     
        try {
           // utx.begin();
            em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
                em.persist(regcheck);
                tx.commit();
         //   utx.commit();
        } catch (Exception ex) {
            try {
                 tx.rollback();
            } catch (Exception re) {
                re.printStackTrace();
            }
             System.out.println("returning true"); 
            
 }
        return regcheck;   
  }
  
  
    User usermob;
  public User findUserByMobileKey(String key)
    {
        EntityManager em = getEntityManager();
        try {

          // User logs = em.createQuery("from User h where h.email = :email and h.password = :passwrd" , User.class )
                  //   set
              Query q= em.createQuery( "from User h where mobilekey = :e" , User.class );
             q.setParameter("e",key);
                 
                 try{
                      System.out.println("######>>>>" + key);
                      usermob=(User) q.getSingleResult();
                    System.out.print("######"+ usermob.getEmail() + "######" + usermob.getPassword() + "#####");
                    } catch (NoResultException nre) {
                      return null; 
                    } 
             System.out.println("returning true"); 
             return usermob; 
                      
                
        } finally {
           // em.close();
        }
        
    }
  
  
  
   User editpassword;
   public User editpasswordmobile(User u)
  {
       editpassword=findUserByMobileKey(u.getMobilekey());
       if(editpassword==null)
           return null;
       //regcheck.setRole(3);
       //regcheck.setDeductionscore(0);
      // regcheck.setStatus(1);
       //regcheck.setMobilekey(GenerateUUID.getUUID());
       editpassword.setPassword(u.getPassword());
      EntityManager em = getEntityManager();
     
        try {
           // utx.begin();
            em = getEntityManager();
            
                tx = em.getTransaction();
		tx.begin();
                em.persist(editpassword);
                tx.commit();
         //   utx.commit();
        } catch (Exception ex) {
            try {
                 tx.rollback();
            } catch (Exception re) {
                re.printStackTrace();
            }
             System.out.println("returning true"); 
            
 }
        return editpassword;   
  }
  
  
  
   
           
           
            List<PublicData> pubdata;
   public List<PublicData> locationchangemobile(User u)
  {
       editpassword=findUserByMobileKey(u.getMobilekey());
       if(editpassword==null)
           return null;
       else 
        pubdata   =getMajorPublicData();
       
       if(pubdata==null)
           return null;
       
       else 
       {
          String s = u.getCity();
          PublicData pnew = new PublicData();
          List<PublicData> lpub = new ArrayList<PublicData>();
          for(PublicData p : pubdata)
          {
              if(p.getOutletcity().matches(s))
              {
                  lpub.add(p);
              }
          }
          return  lpub;
       }
       
     
            
 }
   
   
   List<FinalPoints> userdatalist;
   List<FinalPoints> userdatalistfinal;
    public List<FinalPoints> getAllpointsofUserinList(User u)
    {
        userdatalistfinal=new ArrayList<FinalPoints>();
         editpassword=findUserByMobileKey(u.getMobilekey());
         
         
       if(editpassword==null)
           return null;
       else 
          userdatalist  = editpassword.getUsertotalPointsInOutlet();
       
         for(FinalPoints dd : userdatalist)
         {
             if(!dd.getOutletId().matches("000000000000"))
             {
                 userdatalistfinal.add(dd);
             }
         }
       
       
       if(userdatalistfinal==null)
           return null;
       else
           return userdatalistfinal;
           
    }
   
   
   
       List<PublicData> pubdataall;
   public List<PublicData> locationchangemobileAll(User u)
  {
       editpassword=findUserByMobileKey(u.getMobilekey());
       if(editpassword==null)
           return null;
       else 
        pubdataall   =getMajorPublicData();
       
       if(pubdataall==null)
           return null;
       
       else 
       {
          
          PublicData pnew = new PublicData();
          List<PublicData> lpub = new ArrayList<PublicData>();
          for(PublicData p : pubdataall)
          {
              System.out.println("getting datassss");
                  lpub.add(p);
              
          }
          return  lpub;
       }
       
     
            
 }
   
   
   
          
  }
  
   
   
  
 

