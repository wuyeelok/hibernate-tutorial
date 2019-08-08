package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {

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
