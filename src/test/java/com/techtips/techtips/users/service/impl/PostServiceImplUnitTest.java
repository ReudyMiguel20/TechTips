package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceImplUnitTest {

    @Mock
    private UserServiceImpl userServiceMock;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Saving User in the Database")
    void savingUserToDatabase() {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        User registeredUser = User.builder()
                .id(1L)
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        // Mock
        when(userServiceMock.registerNewUser(newUserRequest)).thenReturn(registeredUser);
        when(userServiceMock.save(any(User.class))).thenReturn(registeredUser);

        // Act
        registeredUser = userServiceMock.registerNewUser(newUserRequest);
        userServiceMock.save(registeredUser);

        // Assert
        verify(userServiceMock, times(1)).save(any(User.class));
        Assertions.assertThat(registeredUser).isInstanceOf(User.class);
    }


    @Test
    @DisplayName("Map Register Request to User Object")
    void mapRegisterRequestToUser() {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        // Act
        User registeredUser = userService.registerNewUser(newUserRequest);

        // Assert
        Assertions.assertThat(registeredUser).isInstanceOf(User.class);
    }

    @Test
    @DisplayName("Verify that Registered User has a Crypted Password")
    void cryptUserPassword() {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        // Act
        User registeredUser = userService.registerNewUser(newUserRequest);

        //Assert
        Assertions.assertThat(registeredUser.getPassword()).contains("$2a$12$");
    }


}