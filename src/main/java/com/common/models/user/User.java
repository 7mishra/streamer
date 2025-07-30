package com.common.models.user; // Adjust your package name

import jakarta.persistence.*; // Use jakarta.persistence for Spring Boot 3+

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Explicitly names the table in MySQL
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY for MySQL's AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 255) // Added email field
    private String email;

    @Column(nullable = false, length = 255) // Length ample for BCrypt hash
    private String password;

    // Default constructor (REQUIRED by JPA/Hibernate)
    public User() {
    }

    // Parameterized constructor for creating new User objects
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters (REQUIRED by JPA/Hibernate for property access)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
