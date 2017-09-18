package cs544.exercise8_2;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {
	private StudentDAO studentdao;
//	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public void setStudentdao(StudentDAO studentdao) {
		this.studentdao = studentdao;
	}

	@Transactional
	public Student getStudent(long studentid) {
		//Transaction tx = sf.getCurrentSession().beginTransaction();
		Student student = studentdao.load(studentid);
		Hibernate.initialize(student.getCourselist());
		//tx.commit();
		return student;
	}
}
