package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {
			// Create 3 student objects
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("John", "Doe", null, "john@luv2.code.com");
			Student tempStudent2 = new Student("Mary", "Public", null, "mary@luv2.code.com");
			Student tempStudent3 = new Student("Bonita", "Appbaum", null, "bonit@luv2.code.com");
			// Start a transaction
			session.beginTransaction();

			// Save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

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

}
