package com.ikechukwu.week8.service.serviceImpl;

import com.ikechukwu.week8.repository.TaskRepository;
import com.ikechukwu.week8.repository.UserRepository;
import com.ikechukwu.week8.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;


    @BeforeEach
    void setUp() {
    }

    @Test
    void registration() {
    }

    @Test
    void login() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void createTask() {
    }

    @Test
    void updateTitleAndDescription() {
    }

    @Test
    void updateTaskStatus() {
    }

    @Test
    void viewAllTasksByStatus() {
    }

    @Test
    void viewAllTasks() {
    }

    @Test
    void getTaskById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void moveForward() {
    }

    @Test
    void moveBackward() {
    }
}