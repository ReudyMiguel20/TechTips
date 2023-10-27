package com.techtips.techtips.auth.service.impl;


import com.techtips.techtips.auth.dto.AuthenticationRequest;
import com.techtips.techtips.auth.dto.AuthenticationToken;
import com.techtips.techtips.auth.service.AuthService;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthServiceImplIntegrationTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthServiceImpl authService;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("techtips_db")
            .withUsername("postgres")
            .withPassword("1111")
            .withReuse(true);

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.close();
        postgreSQLContainer.stop();
        System.out.println("Container stopped and closed.");
    }

    @BeforeEach
    void beforeEach() {
        userService.deleteAllUsers();
    }

    @AfterEach
    void afterEach() {
        userService.deleteAllUsers();
    }


    @Test
    @DisplayName("Generate JWT Token for Registering User")
    void registerNewUserReturnJWTToken() {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        // Act
        AuthenticationToken token = authService.register(newUserRequest);

        //JWT Token always begin with 'eyJhbGciOiJIUzI1NiJ9'
        // Assert
        Assertions.assertThat(token.getToken()).contains("eyJhbGciOiJIUzI1NiJ9");
    }

    @Test
    @DisplayName("Refresh JWT Token To an Existing User in Database")
    void refreshJWTTokenExistingUser() {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        User user = userService.registerNewUserAndAssignRole(newUserRequest);

        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .email(user.getEmail())
                .password("123456789")
                .build();

        //Act
        AuthenticationToken token = authService.authenticateUser(authRequest);

        // JWT Token always begin with 'eyJhbGciOiJIUzI1NiJ9'
        // Assert
        Assertions.assertThat(token.getToken()).contains("eyJhbGciOiJIUzI1NiJ9");
    }

}