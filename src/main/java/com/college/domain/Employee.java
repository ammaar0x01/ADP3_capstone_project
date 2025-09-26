package com.college.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String jobType;
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // FK to User entity

    public Employee() {}

    public Employee(String jobType, LocalDate startDate, User user) {
        this.jobType = jobType;
        this.startDate = startDate;
        this.user = user;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getJobType() {
        return jobType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", jobType='" + jobType + '\'' +
                ", startDate=" + startDate +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}