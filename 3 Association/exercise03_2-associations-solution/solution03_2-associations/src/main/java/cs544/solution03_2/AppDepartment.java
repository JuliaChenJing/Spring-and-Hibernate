package cs544.solution03_2;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppDepartment {

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

            Department department = new Department("accounting");
            Employee employee1 = new Employee("Frank Brown", "062341234");
            Employee employee2 = new Employee("John Doe", "064362738");
            department.addEmployee(employee1);
            department.addEmployee(employee2);
            employee1.setDepartment(department);
            employee2.setDepartment(department);
            session.persist(department);

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
            Collection<Department> departmentList = session.createQuery("from Department").list();
            for (Department dept : departmentList) {
                System.out.println("department name = " + dept.getName());
                for (Employee empl : dept.getEmployeelist()) {
                    System.out.println("employee name= " + empl.getName());
                }
            }
            System.out.println("---- now the reverse -----");
            @SuppressWarnings("unchecked")
            Collection<Employee> employeeList = session.createQuery("from Employee").list();
            for (Employee empl : employeeList) {
                System.out.println("employee name= " + empl.getName());
                System.out.println("department name = " + empl.getDepartment().getName());
            }

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
