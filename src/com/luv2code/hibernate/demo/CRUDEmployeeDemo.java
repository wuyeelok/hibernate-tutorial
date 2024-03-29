package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.service.CreateEmployeeService;
import com.luv2code.hibernate.service.DeleteEmployeeService;
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

			List<Integer> speicalEmpIds = new ArrayList<>();
			speicalEmpIds.add(1);
			speicalEmpIds.add(2);
			speicalEmpIds.add(5);

			List<Employee> speicalEmp = ReadEmployeeService.readEmployeeById(factory, speicalEmpIds);
			System.out.println("\n\nSpecial employee list:");
			speicalEmp.forEach(System.out::println);

			System.out.println("\n\nRead Employee fom same company");
			List<Employee> ecConsulting = ReadEmployeeService.readEmployeeByCompany(factory, "EC Consultant Ltd.");
			ecConsulting.forEach(System.out::println);

			System.out.println("\n\nDelete id 1 & 5");
			List<Integer> deleteIds = new ArrayList<>();
			deleteIds.add(1);
			deleteIds.add(5);

			int deletedRow = DeleteEmployeeService.deleteEmployeeById(factory, deleteIds);
			System.out.println("Deleted row: " + deletedRow);

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
