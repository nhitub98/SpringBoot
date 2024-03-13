package com.example.demo2.repository;

import com.example.demo2.domain.User;
import com.example.demo2.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    public User findById() {
        return User.builder()
                .id("001")
                .userName("nhitub98")
                .password("12345")
                .build();
    }

    public List<UserDTO> findAll() {
        List<User> users = getUsersFromDatabase();
        List<UserDTO> userDTOList = users.stream()
                .map(user -> new UserDTO(user.getId(), user.getUserName()))
                .collect(Collectors.toList());

        return userDTOList;
    }

    private List<User> getUsersFromDatabase() {

        return List.of(
                new User("001", "nhitub98", "12345"),
                new User("002", "quangquang", "3424234"),
                new User("003", "trngv1", "p4324")
        );
    }
}
