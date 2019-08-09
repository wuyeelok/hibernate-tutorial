package com.luv2code.hibernate.demo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = null;

		Session session = null;
		try {
			// Create session factory
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
					.buildSessionFactory();

			// Create session
			session = factory.getCurrentSession();

			int studentId = 1;

			// Start a transaction
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\n\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);
			System.out.println("\n\nMy student: " + myStudent);

			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");

			// commit the transaction
			session.getTransaction().commit();

			// New Code
			session = factory.getCurrentSession();
			session.beginTransaction();

			// Update email for all students.
			System.out.println("Update email for all students");
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Student> cu = cb.createCriteriaUpdate(Student.class);

			cu.from(Student.class);
			cu.set("email", "wuyeelok@gmail.com");

			int affected = session.createQuery(cu).executeUpdate();
			System.out.println("Affected row: " + affected);

			// Commit the transaction
			session.getTransaction().commit();
			if (session != null) {
				session.close();
			}

			System.out.println("DONE!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (factory != null) {
				factory.close();
			}
		}

	}

}
