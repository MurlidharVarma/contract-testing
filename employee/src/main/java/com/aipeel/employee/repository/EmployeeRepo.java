package com.aipeel.employee.repository;

import com.aipeel.employee.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    Optional<Employee> findById(Long id);

    Employee save(Employee employee);
}
