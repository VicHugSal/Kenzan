package com.kenzan.rest.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenzan.rest.spring.entity.Employee;
import com.kenzan.rest.spring.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// autowired the EmployeeService
	@Autowired
	private EmployeeService employeeService; 
	
	// add mapping for GET /employees
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		
		return employeeService.getEmployees();
		
	}
	
	// add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId ) {
		
		Employee theEmployee = employeeService.getEmployee(employeeId);
		
		if(theEmployee == null) {
			throw new  EmployeeNotFoundException("Employee Id not found - " + employeeId);
		}
		
		return theEmployee;
	}
	
	// add mapping for POST/Employees - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case the pass an id in JSON... set id to 0
		// this force a save of new item...instead of update
		theEmployee.setId(0);
		
		employeeService.saveEmployee(theEmployee);
		
		return theEmployee;
		
	}
	
	// Add mapping for  PUT/ employees - update existing employee
	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
			employeeService.saveEmployee(theEmployee);
		
		return theEmployee;
	}
	
	// Add mapping for Delete /customers / {customerId} - delete customer
	// do not follow happy path please
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.getEmployee(employeeId);
		 
		if(tempEmployee == null) {
			throw new  EmployeeNotFoundException("Employee Id not found - " + employeeId);
		}
		
	   employeeService.deleteEmployee(employeeId);
		 return "Delete employee id - " + employeeId;
	}
	
	
}
