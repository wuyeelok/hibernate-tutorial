package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.service.ReadEmployeeService;

public class CRUDEmployeeDemo {

	public static void main(String[] args) {

		// Get session factory
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
					.buildSessionFactory();

			// Get session
			session = factory.getCurrentSession();

			printAllEmployee(session);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			if (factory != null) {
				factory.close();
			}
		}

	}

	private static void printAllEmployee(Session session) {
		System.out.println("\n\nShow all employee in table");
		List<Employee> employees = ReadEmployeeService.readAllEmployee(session);
		employees.forEach(System.out::println);
	}

}
