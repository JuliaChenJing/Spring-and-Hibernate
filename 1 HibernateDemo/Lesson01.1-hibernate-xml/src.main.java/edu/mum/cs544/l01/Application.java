package edu.mum.cs544.l01;

import java.util.List;
//the library about Hibernate
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.mum.cs544.l01.model.Person;

public class Application {
    //create a sessionFatory
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            
            // This step will read hibernate.cfg.xml (written by professor)
            sessionFactory = configuration.configure().buildSessionFactory(); 

        } catch (Throwable ex) {
            System.err.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        //what is a session and what is a transaction, I guess transaction is like a one time thing
        Session session = null;
        Transaction tx = null;
        
        try {
            // you have to get a session by a sessionFactory
            session = sessionFactory.openSession();

            // Create new instance of Person and set values to it(written by professor)
            Person person = new Person();
            person.setFirstname("George");
            person.setLastname("Washington");
            
            Person bimal = new Person();
            bimal.setFirstname("Bimal");
            bimal.setLastname("Parajuli");

            Person julia = new Person();
            julia.setFirstname("Julia");
            julia.setLastname("Chen");
            
            
            
            tx = session.beginTransaction();
            // save the person,put it into the persistant layer
            session.persist(person);
            session.persist(bimal);
            session.persist(julia);
            
            tx.commit();
            output("get ID from detached bean : " + person.getId());

        } catch (HibernateException e) {
            System.err.println(e);
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        
        // ANOTHER SESSION
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve[计] 检索；恢复；重新得到 all the person in the database using SQL
            @SuppressWarnings("unchecked")
            List<Person> persons = 
                session.createQuery("from Person person order by person.firstname").list();

            for (Person p : persons) {
                output("ID=:" + p.getId()
                +" , class="+p.getClass()
                +",First name=" + p.getFirstname()
               + ", Last name= " + p.getLastname());
            }

            tx.commit();

        } catch (HibernateException e) {
            System.err.println(e);
            if (tx != null)  tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    private static void output(String output) {
        System.out.println("================= OUTPUT =================");
        System.out.println(output);     
        System.out.println("==========================================");
    }
}
