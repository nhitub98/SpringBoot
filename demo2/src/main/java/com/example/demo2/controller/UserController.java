package com.example.demo2.controller;

import com.example.demo2.domain.User;
import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.dto.UserDTO;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findOne")
    public UserDTO findOneUser() {
        return userService.findOne(); //trả ra UserDTO không có password
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAllUser() {
        List<UserDTO> userDTOList = userService.findAll();
        return ResponseEntity.ok(userDTOList);
    }
}
