package com.aipeel.project.controller;

import com.aipeel.project.entity.Project;
import com.aipeel.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/employee/{id}")
    public List<Project> getProjectsForEmpId(@PathVariable("id") Long id){
         return projectService.getAllProjectForEmpId(id);
    }

    @PostConstruct
    private void prepopulate(){
        List<String> projects = Arrays.asList("Gene Mapping", "Space Science", "Quantum Computing","Advanced Calculus","Geo-spacial algorithms");
        for (Long i=1L; i<=5L; i++){
            for(String proj: projects) {
                Project p = new Project(i, proj, i.intValue());
                projectService.save(p);
                if(projects.indexOf(proj) == i-1){
                    break;
                }
            }
        }

    }
}
