package com.spring.blogitbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blogitbackend.constants.AppConstants;
import com.spring.blogitbackend.dtos.PostDTO;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.payloads.PostResponse;
import com.spring.blogitbackend.services.FileService;
import com.spring.blogitbackend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    public PostController(PostService postService,FileService fileService) {
        this.fileService = fileService;
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestPart(name = "postDTO") String postDTOJson,
            @PathVariable Long userId,
            @PathVariable Long categoryId,
            @RequestPart(name = "img", required = false) MultipartFile img) {

        ObjectMapper objectMapper = new ObjectMapper();
        PostDTO postDTO;
        try {
            postDTO = objectMapper.readValue(postDTOJson, PostDTO.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (img != null && !img.isEmpty()) {
            ApiResponse resp = fileService.uploadImage(img);
            postDTO.setImageUrl(resp.getMessage());
        }
        PostDTO post = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
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

    @PostMapping("/post/{postId}/img/upload")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam("img") MultipartFile img, @PathVariable Long postId) {

        PostDTO post = postService.getPostById(postId);

        ApiResponse resp = fileService.uploadImage(img);

        post.setImageUrl(resp.getMessage());
        PostDTO updatedPost = postService.updatePost(post, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/liked/posts")
    public ResponseEntity<List<PostDTO>> getLikedPostsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getPostsByUserLikes(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/commented/posts")
    public ResponseEntity<List<PostDTO>> getCommentedPostsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getPostsByUserComments(userId), HttpStatus.OK);
    }
}
