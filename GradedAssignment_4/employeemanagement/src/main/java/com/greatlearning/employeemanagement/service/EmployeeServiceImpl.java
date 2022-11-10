package com.greatlearning.employeemanagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.dao.repository.EmployeeRepository;
import com.greatlearning.employeemanagement.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	// Create
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	// Read
	public Set<Employee> fetchAllEmployees() {
		return new HashSet<>(this.employeeRepository.findAll());
	}

	// Read
	public Employee fetchEmployeeById(long id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id Passed"));
	}

	// Read
	public List<Employee> fetchEmployeeByName(String name) {
		Employee employeeWithNames = new Employee();
		employeeWithNames.setFirstName(name);
		ExampleMatcher exampleMatcher = (ExampleMatcher.matching().withMatcher("firstName",
				ExampleMatcher.GenericPropertyMatchers.exact())).withIgnorePaths("id", "lastName", "email");
		Example<Employee> example = Example.of(employeeWithNames, exampleMatcher);
		return this.employeeRepository.findAll(example);
	}

	// Update
	public Employee updateEmployee(long id, Employee employee) {
		Employee employeeFromRepository = this.fetchEmployeeById(id);
		employeeFromRepository.setEmail(employee.getEmail());
		employeeFromRepository.setFirstName(employee.getFirstName());
		employeeFromRepository.setLastName(employee.getLastName());

		return this.employeeRepository.save(employeeFromRepository);
	}

	// Delete
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	// GetLibrarySortedByName
	public List<Employee> getEmployeeSortedByName(Direction direction){
	return this.employeeRepository.findAll(Sort.by(direction, "firstName"));
	}


}
