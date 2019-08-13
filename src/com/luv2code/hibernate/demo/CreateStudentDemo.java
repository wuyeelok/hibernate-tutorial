package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {
			// Create a student object
			System.out.println("Creating new student object...");

			String theDateOfBirthStr = "23/12/1995";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);

			Student tempStudent = new Student("Paul", "Krugman", theDateOfBirth, "paul@luv2.code.com");

			// Start a transaction
			session.beginTransaction();

			// Save the student object
			System.out.println("Saving the student");
			session.save(tempStudent);

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
