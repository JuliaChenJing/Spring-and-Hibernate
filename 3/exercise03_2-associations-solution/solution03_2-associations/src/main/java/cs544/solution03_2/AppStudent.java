package cs544.solution03_2;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppStudent {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Student student1 = new Student("Frank Brown");
            Student student2 = new Student("John Doe");
            Course course1 = new Course("Programming Languages");
            Course course2 = new Course("Physics");
            course1.addStudent(student1);
            student1.addCourse(course1);
            course1.addStudent(student2);
            student2.addCourse(course1);
            course2.addStudent(student1);
            student1.addCourse(course2);

            session.persist(course1);
            session.persist(course2);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            @SuppressWarnings("unchecked")
            Collection<Student> studentList = session.createQuery("from Student").list();
            for (Student student : studentList) {
                System.out.println("student name = " + student.getName());
                for (Course course : student.getCourselist()) {
                    System.out.println("course name= " + course.getName());
                }
            }
            System.out.println("---- now the reverse -----");
            @SuppressWarnings("unchecked")
            Collection<Course> courseList = session.createQuery("from Course").list();
            for (Course course : courseList) {
                System.out.println("course name= " + course.getName());
                for (Student student : course.getStudentlist()) {
                    System.out.println("student name = " + student.getName());
                }
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.exit(0);
    }
}
