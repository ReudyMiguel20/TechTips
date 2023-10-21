package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.repository.UserRepository;
import com.techtips.techtips.users.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Saving User in the Database")
    void savingUserToDatabase() {
        // Arrange
        User newUser = User.builder()
                .id(1L)
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        newUser = userRepository.save(newUser);

        // Assert
        Assertions.assertThat(newUser).isInstanceOf(User.class);
        Assertions.assertThat(newUser.getId()).isEqualTo(1L);
    }


    @Test
    @DisplayName("Map RegisterRequest DTO to User")
    void mappingRegisterRequestToUser() {
        // Arrange
        RegisterRequest newUnregisteredUser = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        // Act
        User newUser = modelMapper.map(newUnregisteredUser, User.class);

        // Assert
        Assertions.assertThat(newUser).isInstanceOf(User.class);
    }
}