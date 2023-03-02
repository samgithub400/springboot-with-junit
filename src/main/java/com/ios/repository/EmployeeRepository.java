package com.ios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ios.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
