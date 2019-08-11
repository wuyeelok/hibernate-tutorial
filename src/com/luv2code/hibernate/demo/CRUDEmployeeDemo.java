package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.service.CreateEmployeeService;
import com.luv2code.hibernate.service.ReadEmployeeService;
import com.luv2code.hibernate.service.UpdateEmployeeService;

public class CRUDEmployeeDemo {

	public static void main(String[] args) {

		// Get session factory
		SessionFactory factory = null;
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
					.buildSessionFactory();

			printAllEmployee(factory);

			// Create dummy record

			List<Employee> dummyEmp = new ArrayList<>();
			Employee e1 = new Employee("Tom", "Clancy", "UbiSoft");
			Employee e2 = new Employee("James", "Medison", "US");
			Employee e3 = new Employee("Kenneth", "WU", "EDPS");
			dummyEmp.add(e1);
			dummyEmp.add(e2);
			dummyEmp.add(e3);

			CreateEmployeeService.createEmployee(factory, dummyEmp);

			printAllEmployee(factory);

			int changedRow = UpdateEmployeeService.updateEmployeeCompany(factory, "EC Consultant Ltd.");
			System.out.println("\nUpdated Row: " + changedRow);

			printAllEmployee(factory);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (factory != null) {
				factory.close();
			}
		}

	}

	private static void printAllEmployee(SessionFactory factory) {
		System.out.println("\n\nShow all employee in table");
		List<Employee> employees = ReadEmployeeService.readAllEmployee(factory);
		employees.forEach(System.out::println);
	}

}
