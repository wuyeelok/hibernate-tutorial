package com.luv2code.hibernate.demo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

			CriteriaBuilder cb = session.getCriteriaBuilder();

			CriteriaQuery<Student> cq = cb.createQuery(Student.class);

			Root<Student> root = cq.from(Student.class);

			// Query students
			cq.select(root);
			Query<Student> query = session.createQuery(cq);
			List<Student> theStudents = query.getResultList();

			// Display the students
			System.out.println("SHOW ALL students!");
			displayStudents(theStudents);

			// Query students: lastName = 'Doe'
			Predicate lastNameDoe = cb.equal(root.get("lastName"), "Doe");
			cq.select(root).where(lastNameDoe);
			query = session.createQuery(cq);
			theStudents = query.getResultList();

			// Display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);

			// Query students: lastName='Doe' OR firstName='Daffy'
			Predicate firstNameDaffy = cb.equal(root.get("firstName"), "Daffy");
			Predicate doeOrDaffy = cb.or(lastNameDoe, firstNameDaffy);
			cq.select(root).where(doeOrDaffy);
			query = session.createQuery(cq);
			theStudents = query.getResultList();

			// Display the students
			System.out.println("\n\nStudents who have last name of Doe OR first name of Daffy");
			displayStudents(theStudents);

			// Query students where email LIKE '%luv2code.com'
			Predicate emailEndsWithluv2Code = cb.like(root.get("email"), "%luv2.code.com");
			cq.select(root).where(emailEndsWithluv2Code);
			query = session.createQuery(cq);
			theStudents = query.getResultList();

			// Display the students
			System.out.println("\n\nStudents with email ends with luv2.code.com");
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
