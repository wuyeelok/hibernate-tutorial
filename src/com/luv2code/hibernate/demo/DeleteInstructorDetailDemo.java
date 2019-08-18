package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {

			// Start a transaction
			session.beginTransaction();

			// Get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

			// Print the instructor detail
			System.out.println("tempInstructorDetails: " + tempInstructorDetail);

			// Print the associated instructor
			System.out.println("The associaged instructor: " + tempInstructorDetail.getInstructor());

			// Now let's delete the instructor detail
			// Break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);

			// Remove the associated object reference
			session.delete(tempInstructorDetail);
			System.out.println("Deleting the tempInstructorDetail: " + tempInstructorDetail);
			// Commit transaction
			session.getTransaction().commit();

			System.out.println("DONE!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Handle connection leak issue
			if (session != null) {
				session.close();
			}

			if (factory != null) {
				factory.close();
			}
		}

	}

}
