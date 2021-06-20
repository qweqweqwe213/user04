/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author WSR
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session sess=null;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession(){
        if (sess==null){
            sess=getSessionFactory().openSession();
        }
        return sess; 
    }
    
    public static void done(){
        if (sess!=null){
            if (sess.getTransaction().isActive()){
                sess.getTransaction().commit();
            }
            sess.close(); 
        }
        getSessionFactory().close();
    }
      
}