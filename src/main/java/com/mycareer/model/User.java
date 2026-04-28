package com.mycareer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String phone;
    private String department;
    private String userType;
    private String status;
    private LocalDateTime joinDate;
    
    // Constructors
    public User() {}
    
    public User(Long id, String name, String email, String password, String phone, 
                String department, String userType, String status, LocalDateTime joinDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.department = department;
        this.userType = userType;
        this.status = status;
        this.joinDate = joinDate;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }
    public String getUserType() { return userType; }
    public String getStatus() { return status; }
    public LocalDateTime getJoinDate() { return joinDate; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDepartment(String department) { this.department = department; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setStatus(String status) { this.status = status; }
    public void setJoinDate(LocalDateTime joinDate) { this.joinDate = joinDate; }
}