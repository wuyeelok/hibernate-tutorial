package com.luv2code.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Employee;

public class ReadEmployeeService {

	public static List<Employee> readAllEmployee(SessionFactory factory) {
		List<Employee> empRecord = new ArrayList<>();
		Session session = null;
		try {
			// Get session
			session = factory.getCurrentSession();

			// Begin transaction
			session.beginTransaction();

			// Get CriteriaBuilder from session
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Create a criteria query
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

			// Get root
			Root<Employee> root = cq.from(Employee.class);

			// Select all in criteria query
			cq.select(root);

			// Create the actual query in session
			Query<Employee> q = session.createQuery(cq);

			// Get result list
			empRecord = q.getResultList();

			// Commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return empRecord;
	}

}
