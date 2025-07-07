package com.example.usermanagementapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "users") // Specifies the table name in the database
@Data
public class User {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key generation strategy (auto-increment for PostgreSQL)
    private Long id;

    @NotBlank(message = "Name is required") // Validation: Ensures the name field is not null and not empty after trimming
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") // Validation: Size constraint
    private String name;

    @NotBlank(message = "Email is required") // Validation: Ensures the email field is not null and not empty
    @Email(message = "Email should be valid") // Validation: Ensures the email format is valid
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Fetch roles eagerly
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @Column(name = "created_at", updatable = false) // 'updatable = false' ensures it's set only once
    @CreationTimestamp
    private LocalDateTime createdAt;

    // New: Update Timestamp
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
