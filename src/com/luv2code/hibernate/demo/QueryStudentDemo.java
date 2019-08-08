package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {
			// Start a transaction
			session.beginTransaction();

			// Query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// Display the students
			displayStudents(theStudents);

			// Query students: lastName = 'Doe'
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();

			// Display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);

			// Commit transaction
			session.getTransaction().commit();

			System.out.println("DONE!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (factory != null) {
				factory.close();
			}
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
