package com.luv2code.hibernate.demo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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

			if (myStudent != null) {
				// Delete the student
				System.out.println("Deleting student: " + myStudent);
				session.delete(myStudent);
			} else {
				System.out.println("Can not find student with id: " + studentId);
			}

			// commit the transaction
			session.getTransaction().commit();

			// Delete student id = 2 using query
			System.out.println("\n\nDeleting student id = 2...");

			// Get the session
			session = factory.getCurrentSession();

			// Begin transaction
			session.beginTransaction();

			// Get the criteria builder
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Get criteria delete
			CriteriaDelete<Student> cd = cb.createCriteriaDelete(Student.class);

			// Set root
			Root<Student> r = cd.from(Student.class);

			// Set condition
			Predicate idTwo = cb.equal(r.get("id"), 2);
			cd.where(idTwo);

			// Call query
			int deletedRow = session.createQuery(cd).executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Deleted student row: " + deletedRow);

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
