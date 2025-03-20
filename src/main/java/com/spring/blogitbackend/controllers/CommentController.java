package com.spring.blogitbackend.controllers;

import com.spring.blogitbackend.dtos.CommentDTO;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long userId,@PathVariable Long postId,@Valid @RequestBody CommentDTO commentDTO) {
        CommentDTO comment = commentService.saveComment(commentDTO, postId, userId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable Long postId)
    {
        List<CommentDTO> comments = commentService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId,@Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(commentDTO, commentId), HttpStatus.OK);
    }
}
