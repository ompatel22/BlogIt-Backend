package com.spring.blogitbackend.payloads;

import com.spring.blogitbackend.dtos.PostDTO;
import com.spring.blogitbackend.entities.Post;

import java.util.List;

public class PostResponse
{
    private List<PostDTO> posts;
    private int pageNumber;
    private int pageSize;
    private int totalPosts;
    private int totalPages;
    private boolean lastPage;

    public PostResponse(){
    }

    public PostResponse(List<PostDTO> posts, int pageNumber, int pageSize, int totalPosts, boolean lastPage){
        this.posts = posts;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPosts = totalPosts;
        this.lastPage = lastPage;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
