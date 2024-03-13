package com.example.demo2.service;

import com.example.demo2.domain.User;
import com.example.demo2.dto.UserDTO;
import com.example.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findOne() {
        User user = userRepository.findById();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        return userDTO;
    }

    public List<UserDTO> findAll() {
        List<UserDTO> users = userRepository.findAll();
        List<UserDTO> userDTOList = users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUserName(user.getUserName());

                    return userDTO;
                })
                .collect(Collectors.toList());
        return userDTOList;
    }
}
