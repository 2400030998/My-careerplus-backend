package com.mycareer.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String className;
    private String classCode;
    private String subject;
    private String section;
    private String description;
    private LocalDateTime createdAt;
    
    // Add this relationship
    @ManyToMany
    @JoinTable(
        name = "class_students",
        joinColumns = @JoinColumn(name = "class_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<User> students = new ArrayList<>();
    
    // Add this method
    public void addStudent(User student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}