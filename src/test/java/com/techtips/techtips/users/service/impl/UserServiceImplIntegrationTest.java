//package com.techtips.techtips.users.service.impl;
//
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserServiceImplIntegrationTest {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("techtips_db")
//            .withUsername("postgres")
//            .withPassword("1111")
//            .withReuse(true);
//
//    //    @DynamicPropertySource
////    static void configureProperties(DynamicPropertyRegistry registry) {
////        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
////        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
////        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
////    }
//
//    @BeforeAll
//    static void beforeAll() {
//        postgreSQLContainer.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgreSQLContainer.close();
//        postgreSQLContainer.stop();
//        System.out.println("Container stopped and closed.");
//    }
//}
