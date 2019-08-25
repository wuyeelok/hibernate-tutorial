package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {

			// Start a transaction
			session.beginTransaction();

			// Get the instructor from DB
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("luv2code: Instructor: " + tempInstructor);

			// Get course for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

			// Commit transaction
			session.getTransaction().commit();

			System.out.println("luv2code: DONE!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (factory != null) {
				factory.close();
			}
		}

	}

}
