package com.spring.blogitbackend.dtos;

import com.spring.blogitbackend.entities.Post;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    @NotBlank
    private String content;
    private String username;
    private LocalDateTime createdAt;

    public CommentDTO() {}

    public CommentDTO(Long id, String content, String username, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
