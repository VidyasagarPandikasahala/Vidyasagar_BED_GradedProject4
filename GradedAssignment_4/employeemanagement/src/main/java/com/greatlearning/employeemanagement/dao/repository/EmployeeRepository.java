package com.greatlearning.employeemanagement.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeemanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
