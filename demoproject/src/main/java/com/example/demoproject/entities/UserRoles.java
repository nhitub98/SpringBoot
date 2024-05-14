package com.example.demoproject.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_roles")
public class UserRoles {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private Integer role;

    @Column(name = "username")
    private String username;


}
