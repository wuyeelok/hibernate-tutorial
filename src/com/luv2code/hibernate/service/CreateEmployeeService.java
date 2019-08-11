package com.luv2code.hibernate.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.luv2code.hibernate.demo.entity.Employee;

public class CreateEmployeeService {

	public static void createEmployee(SessionFactory factory, List<Employee> employees) {

		Session session = null;

		try {
			session = factory.getCurrentSession();

			// Begin transaction
			session.beginTransaction();

			// Loop list and save
			for (Employee e : employees) {
				session.save(e);
			}

			// Commit transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
