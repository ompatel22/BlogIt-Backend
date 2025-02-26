package com.spring.blogitbackend.services;

import com.spring.blogitbackend.dtos.PostDTO;
import com.spring.blogitbackend.entities.Category;
import com.spring.blogitbackend.entities.Post;
import com.spring.blogitbackend.entities.User;
import com.spring.blogitbackend.exceptions.ResourceNotFoundException;
import com.spring.blogitbackend.repositories.CategoryRepository;
import com.spring.blogitbackend.repositories.PostRepository;
import com.spring.blogitbackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostDTO createPost(PostDTO postDTO, Long uid, Long cid) {
        Category category = categoryRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Category", "id", cid));
        User user = userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User","id",uid));
        Post post = modelMapper.map(postDTO, Post.class);

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setCategory(category);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);

        return modelMapper.map(postRepository.save(post), PostDTO.class);
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    public PostDTO getPostById(Long pid){
        Post post= postRepository.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Post","id",pid));
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO updatePost(PostDTO postDTO,Long pid) {
        Post post=postRepository.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Post","id",pid));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(postRepository.save(post), PostDTO.class);
    }

    public boolean deletePost(Long pid) {
        if(!postRepository.existsById(pid)){
            throw new ResourceNotFoundException("Post","id",pid);
        }
        postRepository.deleteById(pid);
        return true;
    }

    public List<PostDTO> getPostByCategory(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = postRepository.findByCategory(cat);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    public List<PostDTO> getPostsByUser(Long uid){
        User user = userRepository.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","id",uid));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

//    public List<PostDTO> searchPosts(String keyword){}
}
