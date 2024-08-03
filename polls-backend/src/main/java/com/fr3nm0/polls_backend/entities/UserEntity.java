package com.fr3nm0.polls_backend.entities;


import com.fr3nm0.polls_backend.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})
})

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable=false, length = 255)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private Set<Role> roles;
}
