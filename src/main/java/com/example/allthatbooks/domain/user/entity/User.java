package com.example.allthatbooks.domain.user.entity;

import com.example.allthatbooks.domain.common.entity.Timestamped;
import com.example.allthatbooks.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String address;

    private LocalDateTime deletedAt;
}
