package com.luv2code.hibernate.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.luv2code.hibernate.demo.entity.Employee;

public class UpdateEmployeeService {

	public static int updateEmployeeCompany(SessionFactory factory, String company) {
		int affectedRow = 0;

		Session session = null;
		try {
			// Get session from factory
			session = factory.getCurrentSession();

			// Begin transaction
			session.beginTransaction();

			// Get criteria builder
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Create criteriaUpdate query
			CriteriaUpdate<Employee> cu = cb.createCriteriaUpdate(Employee.class);
			cu.from(Employee.class);
			cu.set("company", company);

			// Create the query and execute update
			affectedRow = session.createQuery(cu).executeUpdate();

			// Commit transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return affectedRow;
	}

}
