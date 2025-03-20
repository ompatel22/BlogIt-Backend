package com.spring.blogitbackend.controllers;

import com.spring.blogitbackend.dtos.LikeDTO;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/user/{userId}/post/{postId}/likes")
    public ResponseEntity<LikeDTO> like(@PathVariable Long userId, @PathVariable Long postId) {
        return new ResponseEntity<>(likeService.doLike(postId,userId), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/likes")
    public ResponseEntity<List<LikeDTO>> getLikes(@PathVariable Long postId) {
        return new ResponseEntity<>(likeService.getAllLikes(postId), HttpStatus.OK);
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<ApiResponse> unlike(@PathVariable Long likeId) {
        return new ResponseEntity<>(likeService.doUnlike(likeId),HttpStatus.OK);
    }
}
