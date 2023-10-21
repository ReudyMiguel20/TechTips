package com.techtips.techtips.users.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name shouldn't be null")
    @NotEmpty(message = "First name shouldn't be null")
    private String firstName;

    @NotNull(message = "Last name shouldn't be null")
    @NotEmpty(message = "Last name shouldn't be null")
    private String lastName;

    @NotNull(message = "Email shouldn't be null")
    @NotEmpty(message = "Email shouldn't be null")
    private String email;

    @NotNull(message = "Password shouldn't be null")
    @NotEmpty(message = "Password shouldn't be null")
    private String password;

}
