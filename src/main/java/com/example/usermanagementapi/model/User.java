package com.example.usermanagementapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "users") // Specifies the table name in the database
public class User {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key generation strategy (auto-increment for PostgreSQL)
    private Long id;

    @NotBlank(message = "Name is required") // Validation: Ensures the name field is not null and not empty after trimming
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") // Validation: Size constraint
    private String name;

    @NotBlank(message = "Email is required") // Validation: Ensures the email field is not null and not empty
    @Email(message = "Email should be valid") // Validation: Ensures the email format is valid
    private String email;

    @Column(name = "created_at", updatable = false) // 'updatable = false' ensures it's set only once
    @CreationTimestamp
    private LocalDateTime createdAt;

    // New: Update Timestamp
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Default constructor is required by JPA
    public User() {
    }

    // Constructor with fields (optional, but good for testing/convenience)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters for all fields
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
