package com.luv2code.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.luv2code.hibernate.demo.entity.Employee;

public class DeleteEmployeeService {

	public static int deleteEmployeeById(SessionFactory factory, List<Integer> empIds) {
		int deletedRow = 0;

		Session session = null;
		try {
			// Get session from factory
			session = factory.getCurrentSession();

			// Begin transaction
			session.beginTransaction();

			// Get criteria builder
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Get criteria delete
			CriteriaDelete<Employee> cd = cb.createCriteriaDelete(Employee.class);

			// Set up predicate
			Root<Employee> root = cd.from(Employee.class);
			Predicate finalOrPredicate = null;
			List<Predicate> orPredicates = new ArrayList<>();

			for (Integer empId : empIds) {
				Predicate equalEmpId = cb.equal(root.get("employeeId"), empId);
				orPredicates.add(equalEmpId);
			}
			finalOrPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));

			cd.where(finalOrPredicate);

			deletedRow = session.createQuery(cd).executeUpdate();

			// Commit transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return deletedRow;
	}
}
