package com.ikechukwu.week8.service.serviceImpl;

import com.ikechukwu.week8.dto.TaskDTO;
import com.ikechukwu.week8.dto.UserDTO;
import com.ikechukwu.week8.enums.Status;
import com.ikechukwu.week8.model.Task;
import com.ikechukwu.week8.model.User;
import com.ikechukwu.week8.repository.TaskRepository;
import com.ikechukwu.week8.repository.UserRepository;
import com.ikechukwu.week8.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Calendar.AUGUST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    private User user;
    private UserDTO userDTO;
    private Task task;
    private TaskDTO taskDTO;
//    private LocalDateTime time;

    @BeforeEach
    void setUp() {
        LocalDateTime time = LocalDateTime.of(2022, AUGUST, 3, 6, 30,40,50000);
        List<Task> taskList = new ArrayList<>();
        user = new User(1, "Mike Robinson", "MikeRob", "password", taskList);
        task = new Task(1, "Write code", "Code till 7am", Status.PENDING, time, time, time, user);
        taskDTO = new TaskDTO("Task 1", "Learn Spring Boot", Status.PENDING);
        userDTO = new UserDTO("Mike Robinson", "MikeRob", "password");

        when(userRepository.save(user)).thenReturn(user);
        when(taskRepository.save(task)).thenReturn(task);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        when(userRepository.findUserByUsername("MikeRob")).thenReturn(Optional.of(user));


    }

    @Test
    void registration() {
        when(userServiceImpl.registration(userDTO)).thenReturn(user);
        var actual = userServiceImpl.registration(userDTO);
        var expected = user;
        assertEquals(expected, actual);
    }

    @Test
    void login_successful() {
        String message = "Success";
        var actual = userServiceImpl.login("MikeRob", "password");
        var expected = message;
        assertEquals(expected, actual);
    }

    @Test
    void login_notSuccessful() {
        String message = "Incorrect Password";
        var actual = userServiceImpl.login("MikeRob", "pass");
        var expected = message;
        assertEquals(expected, actual);
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