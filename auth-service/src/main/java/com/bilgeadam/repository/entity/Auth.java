package com.bilgeadam.repository.entity;


import com.bilgeadam.repository.enums.Roles;
import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles Role = Roles.USER;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;
    private String activatedCode;


}
