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
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplUnitTest {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;


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
        when(userService.registerNewUser(newUserRequest)).thenReturn(registeredUser);
        when(userService.save(any(User.class))).thenReturn(registeredUser);

        // Act
        registeredUser = userService.registerNewUser(newUserRequest);
        userService.save(registeredUser);

        // Assert
        verify(userService, times(1)).save(any(User.class));
        Assertions.assertThat(registeredUser).isInstanceOf(User.class);
    }


//    @Test
//    @DisplayName("Map RegisterRequest DTO to User")
//    void mappingRegisterRequestToUser() {
//        // Arrange
//        RegisterRequest newUnregisteredUser = RegisterRequest.builder()
//                .firstName("Mike")
//                .lastName("Carmine")
//                .email("MikeCarmine@gmail.com")
//                .password("123456789")
//                .build();
//
//        when(userService.registerNewUser(newUnregisteredUser))
//
//        // Act
//        User registeredNewUser = userService.registerNewUser(newUnregisteredUser);
//
//        // Assert
//        Assertions.assertThat(registeredNewUser).isInstanceOf(User.class);
//    }


}