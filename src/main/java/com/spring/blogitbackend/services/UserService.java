package com.spring.blogitbackend.services;

import com.spring.blogitbackend.dtos.UserDTO;
import com.spring.blogitbackend.entities.User;
import com.spring.blogitbackend.exceptions.ResourceNotFoundException;
import com.spring.blogitbackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User existingUserByUserName = userRepository.findByUsername(userDTO.getUsername());
        User existUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        if(existingUserByUserName == null && existUserByEmail == null) {
            user = userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
        throw new RuntimeException("User already exists");
    }

    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User","id",id);
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOs;
    }

    public UserDTO login(String usernameOrEmail, String password) {
        String email = "";
        String username="";
        if(usernameOrEmail.contains("@")){
            email = usernameOrEmail;
        }
        else {
            username=usernameOrEmail;
        }
        if(!email.isEmpty()){
            System.out.println(email);
            User user = userRepository.findByEmail(email);
            if(user != null) {
                if(user.getPassword().equals(password)) {
                    return modelMapper.map(user, UserDTO.class);
                }
            }
//            throw new RuntimeException("Invalid email or password");
        }
        else if(!username.isEmpty()){
            System.out.println(username);
            User user = userRepository.findByUsername(username);
            if(user != null) {
                if(user.getPassword().equals(password)) {
                    return modelMapper.map(user, UserDTO.class);
                }
            }
//            throw new RuntimeException("Invalid email or password");
        }
        throw new RuntimeException("Invalid email or password");
    }

}
