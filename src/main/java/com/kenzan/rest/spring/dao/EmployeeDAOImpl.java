package com.kenzan.rest.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kenzan.rest.spring.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Employee> getEmployees() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Employee> theQuery = 
				currentSession.createQuery("from Employee order by lastName",
											Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
				
		// return the results		
		return employees;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the employee ... finally LOL
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public Employee getEmployee(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();		
	}

}











