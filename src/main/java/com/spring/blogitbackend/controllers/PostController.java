package com.spring.blogitbackend.controllers;

import com.spring.blogitbackend.dtos.PostDTO;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.payloads.PostResponse;
import com.spring.blogitbackend.services.PostService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long categoryId) {
        PostDTO post = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long postId) {
        return new ResponseEntity<>(postService.updatePost(postDTO, postId), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        boolean success = postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post successfully deleted!", success), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsByUserId(@PathVariable Long userId) {
        List<PostDTO> postDTOS = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsByCategory(@PathVariable Long categoryId) {
        List<PostDTO> postDTOS = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> serachPostByTitle(@PathVariable String keyword) {
        List<PostDTO> postDTOS = postService.searchPosts(keyword);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }
}
