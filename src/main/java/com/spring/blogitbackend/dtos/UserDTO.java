package com.spring.blogitbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "username must be minimum of 4 characters")
    private String username;
    @NotEmpty
    @Size(min = 4, message = "password must be minimum of 4 characters")
    private String password;
    @Email
    private String email;
    @NotEmpty
    private String about;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String email, String about) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
