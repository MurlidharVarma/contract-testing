package com.aipeel.project.repository;

import com.aipeel.project.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
    List<Project> findAllByEmployeeId(Long empId);
}
