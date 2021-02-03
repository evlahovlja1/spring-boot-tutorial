package com.example.demo.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity //for hibernate
@Table //for table in our db
public class Student {
    @Id
    @SequenceGenerator( //creates sequence
            name = "student_sequence", //for classes in program
            sequenceName = "student_sequence", //for db (if we created sequence in, let's say oracle, name of that seq goes here)
            allocationSize = 1
    )
    @GeneratedValue( //uses sequence
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    @JsonFormat(pattern = "yyyy-M-dd")
    private LocalDate dob;

    @Transient
    private Integer age;

    public Student() {
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Math.abs(Period.between(LocalDate.now(), this.dob).getYears());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
