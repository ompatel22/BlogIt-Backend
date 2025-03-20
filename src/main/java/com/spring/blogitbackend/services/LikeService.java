package com.spring.blogitbackend.services;

import com.spring.blogitbackend.dtos.LikeDTO;
import com.spring.blogitbackend.entities.Like;
import com.spring.blogitbackend.entities.Post;
import com.spring.blogitbackend.entities.User;
import com.spring.blogitbackend.exceptions.ResourceNotFoundException;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.repositories.LikeRepository;
import com.spring.blogitbackend.repositories.PostRepository;
import com.spring.blogitbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public LikeDTO doLike(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like = likeRepository.save(like);
        return convertToLikeDTO(like);
    }

    public List<LikeDTO> getAllLikes(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        List<LikeDTO> likeDTOS = post.getLikes().stream().map(likeDTO -> convertToLikeDTO(likeDTO)).collect(Collectors.toList());
        return likeDTOS;
    }

    public ApiResponse doUnlike(Long likeId){
        if(likeRepository.existsById(likeId)){
            likeRepository.deleteById(likeId);
            return new ApiResponse("successfully Unliked Post",true);
        }
        throw new ResourceNotFoundException("Like", "id", likeId);
    }

    public LikeDTO convertToLikeDTO(Like like) {
        return new LikeDTO(like.getId(),like.getUser().getId(),like.getUser().getUsername());
    }
}
