package com.spring.blogitbackend.services;

import com.spring.blogitbackend.dtos.CommentDTO;
import com.spring.blogitbackend.entities.Comment;
import com.spring.blogitbackend.entities.Post;
import com.spring.blogitbackend.entities.User;
import com.spring.blogitbackend.exceptions.ResourceNotFoundException;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.repositories.CommentRepository;
import com.spring.blogitbackend.repositories.PostRepository;
import com.spring.blogitbackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public CommentDTO saveComment(CommentDTO comment, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Comment cmt = modelMapper.map(comment,Comment.class);
        cmt.setPost(post);
        cmt.setUser(user);
        cmt.setCreatedAt(LocalDateTime.now());
        cmt = commentRepository.save(cmt);
        //return modelMapper.map(cmt,CommentDTO.class);
        return convertToCommentDTO(cmt);
    }

    public CommentDTO updateComment(CommentDTO comment, Long postId) {
        Comment cmt = commentRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", postId));
        cmt.setContent(comment.getContent());
        cmt.setCreatedAt(LocalDateTime.now());
        cmt = commentRepository.save(cmt);
        return convertToCommentDTO(cmt);
    }

    public List<CommentDTO> getAllComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = post.getComments();
        return comments.stream().map(comment -> convertToCommentDTO(comment)).collect(Collectors.toList());
    }

    public ApiResponse deleteComment(Long commentId) {
        Comment cmt= commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        commentRepository.delete(cmt);
        return new ApiResponse("successfully deleted!",true);
    }

    public CommentDTO convertToCommentDTO(Comment cmt) {
        return new CommentDTO(cmt.getId(),cmt.getContent(),cmt.getUser().getUsername(),cmt.getCreatedAt());
    }
}
