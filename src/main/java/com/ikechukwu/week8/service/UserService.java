package com.ikechukwu.week8.service;

import com.ikechukwu.week8.dto.TaskDTO;
import com.ikechukwu.week8.dto.UserDTO;
import com.ikechukwu.week8.model.Task;
import com.ikechukwu.week8.model.User;

import java.util.List;

public interface UserService {

    User registration(UserDTO userDTO);

    String login(String username, String password);

    Task createTask(TaskDTO taskDTO, long id);

    Task updateTitleAndDescription(TaskDTO taskDTO, long id);

    boolean updateTaskStatus(String status, long id);

    List<Task> viewAllTasks(long id);

    Task getTaskById(long id);

    List<Task> viewAllTasksByStatus(String status);

    boolean deleteById(long id);

    public User getUserByUsername(String email);

    String moveForward(long id);

    String moveBackward(long id);
}
