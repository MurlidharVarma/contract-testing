package com.aipeel.project.service;

import com.aipeel.project.entity.Project;
import com.aipeel.project.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo repo;

    public List<Project> getAllProjectForEmpId(Long empId){
        return repo.findAllByEmployeeId(empId);
    }

    public Project save(Project p){
        return repo.save(p);
    }
}
