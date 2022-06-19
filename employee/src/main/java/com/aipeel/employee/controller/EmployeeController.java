package com.aipeel.employee.controller;

import com.aipeel.employee.entity.Employee;
import com.aipeel.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeWithProjects(id);
    }

    @PostConstruct
    private void prepopulate(){
        List<String> emps = Arrays.asList("Murli","Divya","Priyanka","Aadi","Sajit");
        for(String emp: emps){
            Employee employee = new Employee(emp);
            employeeService.save(employee);
        }
    }
}
