package com.greatlearning.employeemanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagement.entity.Employee;
import com.greatlearning.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeMainController {
	@Autowired
	private  EmployeeService employeeService;
	
	
	@PostMapping
	public void saveEmployee(@RequestBody Employee employee) {
		this.employeeService.saveEmployee(employee);
	}

	@GetMapping
	public Set<Employee> fetchAllEmployee() {
		return this.employeeService.fetchAllEmployees();
	}

	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long id) {
		return this.employeeService.fetchEmployeeById(id);
	}

	@GetMapping("/fetch-by-Name/{name}")
	public List<Employee> fetchEmployeeByName(@PathVariable("name") String name) {
		return this.employeeService.fetchEmployeeByName(name);
	}

	@PutMapping("/{id}")
	public Employee updateEmployeeDetails(@PathVariable("id") long id, @RequestBody Employee employee) {
		return this.employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long id) {
		this.employeeService.deleteEmployeeById(id);
	}

	@GetMapping("/getEmployeeSorted/{direction}")
	public List<Employee> fetchEmployeeSortedByName(@PathVariable("direction") Direction direction) {
		return this.employeeService.getEmployeeSortedByName(direction);
	}


}
