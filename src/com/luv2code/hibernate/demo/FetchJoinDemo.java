package com.luv2code.hibernate.demo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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

			// Get CriteriaBuilder from session
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Create a criteria query
			CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);

			// Get root
			Root<Instructor> root = cq.from(Instructor.class);
			root.fetch("courses", JoinType.LEFT);

			Predicate equalInstructorId = cb.equal(root.get("id"), theId);

			cq.select(root).where(equalInstructorId);

			Query<Instructor> query = session.createQuery(cq);

			// Execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();

			System.out.println("luv2code: Instructor: " + tempInstructor);

			// Commit transaction
			session.getTransaction().commit();

			// Close the session
			session.close();

			System.out.println("\nluv2code: The session is now closed!\n");

			// Get course for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

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
