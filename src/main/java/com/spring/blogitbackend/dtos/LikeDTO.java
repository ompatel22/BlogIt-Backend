package com.spring.blogitbackend.dtos;

public class LikeDTO {
    private Long id;
    private Long userId;
    private String username;

    public LikeDTO() {
    }

    public LikeDTO(Long id, Long uid, String username) {
        this.id = id;
        this.userId = uid;
        this.username = username;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
