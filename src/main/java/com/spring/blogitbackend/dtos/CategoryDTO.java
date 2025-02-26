package com.spring.blogitbackend.dtos;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {
    private Long id;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
