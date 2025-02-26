package com.spring.blogitbackend.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private CategoryDTO category;
    private UserDTO user;

    public PostDTO(Long id, String title, String content, String imageUrl, CategoryDTO category, LocalDateTime createdAt, UserDTO user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.user = user;
    }

    public PostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
