package com.example.employee_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_service.models.Employee;
import com.example.employee_service.repositories.EmployeeRepository;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(String id) {
        return employeeRepository.findEmployeeById(id);
    }
}
