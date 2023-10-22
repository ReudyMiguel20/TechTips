package com.techtips.techtips.users.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplIntegrationTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserServiceImpl userService;


    @Mock
    private MockMvc mockMvc;

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

    @Test
    void createNewUserAndReturn() throws Exception {
        // Arrange
        RegisterRequest newUserRequest = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Carmine")
                .email("MikeCarmine@gmail.com")
                .password("123456789")
                .build();

        //Act
        User registeredUser = userService.registerNewUser(newUserRequest);
        userService.save(registeredUser);

        //Assert
        Assertions.assertThat(registeredUser).isInstanceOf(User.class);
        Assertions.assertThat(registeredUser).isNotNull();
        Assertions.assertThat(registeredUser.getId()).isEqualTo(1L);

        //        String jsonRequest = objectMapper.writeValueAsString(request);

//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/users/register")
//                        .contentType("application/json")
//                        .content(jsonRequest)
//                        .accept("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }
}
