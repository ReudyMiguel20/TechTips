package com.techtips.techtips.users.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtips.techtips.users.exception.error.UserNotFoundException;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplIntegrationTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserServiceImpl userService;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("techtips_db")
            .withUsername("postgres")
            .withPassword("1111")
            .withReuse(true);

    //    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//    }

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
    @DisplayName("Given a Register Request, creates a New User and persist to Database")
    void createNewUserAndReturn() throws Exception {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        //Act
        User registeredUser = userService.registerNewUserAndAssignRole(newUserRequest);
        userService.save(registeredUser);

        //Assert
        Assertions.assertThat(registeredUser).isInstanceOf(User.class);
        Assertions.assertThat(registeredUser).isNotNull();
        Assertions.assertThat(registeredUser.getId()).isInstanceOf(Long.class);

        //        String jsonRequest = objectMapper.writeValueAsString(request);

//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/users/register")
//                        .contentType("application/json")
//                        .content(jsonRequest)
//                        .accept("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    @DisplayName("Delete a user by ID from the database")
    void deleteUserById() {
        // Arrange
        User firstUser = User.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        User secondUser = User.builder()
                .firstName("Marcus")
                .lastName("Tedgief")
                .email("Marcustedgief@gmail.com")
                .password("codingisfun")
                .build();

        userService.save(firstUser);
        userService.save(secondUser);

        // Act
        userService.deleteUserById(1L);

        // Assert
        Assert.assertThrows(UserNotFoundException.class, () -> {
            userService.findById(1L);
        });

        Assertions.assertThat(userService.getAllUsers()).isNotEmpty();
    }

    @Test
    @DisplayName("Create Two Users: First User should be ADMIN, Second User should be USER")
    void createTwoUsersWithRoles() {
        // Arrange
        RegisterRequest firstUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        RegisterRequest secondUserRequest = RegisterRequest.builder()
                .firstName("Marcus")
                .lastName("Tedgief")
                .email("Marcustedgief@gmail.com")
                .password("codingisfun")
                .build();

        User firstUser = userService.registerNewUserAndAssignRole(firstUserRequest);
        User secondUser = userService.registerNewUserAndAssignRole(secondUserRequest);

        // Act
        userService.save(firstUser);
        userService.save(secondUser);

        // Assert
        Assertions.assertThat(firstUser.getRole().toString()).isEqualTo("ADMIN");
        Assertions.assertThat(secondUser.getRole().toString()).isEqualTo("USER");
    }


}
