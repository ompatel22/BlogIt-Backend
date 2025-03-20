package com.spring.blogitbackend.controllers;

import com.spring.blogitbackend.dtos.LoginRequest;
import com.spring.blogitbackend.dtos.UserDTO;
import com.spring.blogitbackend.payloads.ApiResponse;
import com.spring.blogitbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginRequest request) {
        System.out.println(request.getUsernameOrEmail());
        System.out.println(request.getPassword());
        UserDTO userDTO = userService.login(request.getUsernameOrEmail(), request.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
        UserDTO updatedUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("user deleted!",true),HttpStatus.OK);
    }
}
