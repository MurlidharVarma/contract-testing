package com.aipeel.employee.service;

import com.aipeel.employee.entity.Employee;
import com.aipeel.employee.repository.EmployeeRepo;
import com.aipeel.employee.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    RestTemplate restTemplate = new RestTemplate();

    @Value("${project.host}")
    String projectHost;

    @Value("${project.listById}")
    String projectListById;

    public Optional<Employee> getEmployeeWithProjects(Long id){
        Optional<Employee> employee = employeeRepo.findById(id);

        ResponseEntity<Project[]> results = this.restTemplate.getForEntity(projectHost+projectListById+id, Project[].class);
        employee.get().setProjects(results.getBody());

        return employee;
    }

    public Employee save(Employee emp){
        return employeeRepo.save(emp);
    }
}
