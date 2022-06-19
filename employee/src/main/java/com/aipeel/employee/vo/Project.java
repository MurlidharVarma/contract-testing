package com.aipeel.employee.vo;

public class Project {
    private Long id;
    private Long employeeId;
    private String projectName;
    private Integer size;

    public Project(){}

    public Project(Long id, Long employeeId, String projectName, Integer size) {
        this.id = id;
        this.employeeId = employeeId;
        this.projectName = projectName;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
