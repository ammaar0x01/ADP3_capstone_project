package com.college.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//@Getter
//@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String name;
    private String surname;
    private int age;
    private String jobType;
    private String gender;
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // FK to User entity

    public Employee() {
    }

    public Employee(String name, String surname, int age, String jobType, String gender, LocalDate startDate,
            User user) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.jobType = jobType;
        this.gender = gender;
        this.startDate = startDate;
        this.user = user;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getJobType() {
        return jobType;
    }

    public String getGender() {
        return gender;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
 // Cant declare user_id in toString because there in got get user id in user class
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", jobType='" + jobType + '\'' +
                ", gender='" + gender + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
