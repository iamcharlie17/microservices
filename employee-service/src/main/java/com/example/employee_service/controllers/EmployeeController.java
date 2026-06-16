package com.example.employee_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_service.models.Employee;
import com.example.employee_service.services.EmployeeService;


@CrossOrigin(origins = "https://hoppscotch.io/")

@RestController
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }


    @GetMapping("/{id}")
    public Employee findById(@RequestParam String id) {
        return employeeService.findById(id);
    }

}
