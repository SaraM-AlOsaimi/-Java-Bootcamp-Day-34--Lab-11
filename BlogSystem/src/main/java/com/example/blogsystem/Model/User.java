package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
public class User {

    public User(Integer userId, String username, String password, String email, LocalDate registrationDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    // user_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;

    //username
    @NotEmpty(message = "Username is empty")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String username;

    //password
    @NotEmpty(message = "Password is empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$" , message = "Your password must be between 8 and 16 characters long, contain at least one lowercase letter, one uppercase letter, and one number.")
    @Column(columnDefinition = "varchar(16) not null")
    private String password;

    //email
    @NotBlank(message = "Email is blank")
    @Email(message = "Enter a valid email format")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    //registration_date
    private LocalDate registrationDate;
}
