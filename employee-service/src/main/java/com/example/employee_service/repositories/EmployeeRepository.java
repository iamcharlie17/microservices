package com.example.employee_service.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.employee_service.models.Employee;



public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findEmployeeById(String id);

}
