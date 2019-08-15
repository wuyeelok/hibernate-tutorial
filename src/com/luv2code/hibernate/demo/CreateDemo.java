package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {

			// Create the objects
			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
					"Luv 2 code!!!");

			// Associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// Start a transaction
			session.beginTransaction();

			// Save the instructor
			// This will ALSO save the details object
			// because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

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
