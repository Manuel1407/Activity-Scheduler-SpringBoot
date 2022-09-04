package com.ikechukwu.week8.service.serviceImpl;

import com.ikechukwu.week8.dto.TaskDTO;
import com.ikechukwu.week8.dto.UserDTO;
import com.ikechukwu.week8.enums.Status;
import com.ikechukwu.week8.exception.UserNotFoundException;
import com.ikechukwu.week8.model.Task;
import com.ikechukwu.week8.model.User;
import com.ikechukwu.week8.repository.TaskRepository;
import com.ikechukwu.week8.repository.UserRepository;
import com.ikechukwu.week8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public User registration(UserDTO userDTO) {
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        String message;
        User user = getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            message = "Success";
        } else {
            message = "Incorrect Password";
        }
        return message;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("This user was not found"));
    }

    @Override
    public Task createTask(TaskDTO taskDTO, long id) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
        task.setUser(user);
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Status.PENDING);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTitleAndDescription(TaskDTO taskDTO, long id) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public boolean updateTaskStatus(String status, long id) {
        return taskRepository.updateTaskByIdAndStatus(status, id);
    }

    @Override
    public List<Task> viewAllTasksByStatus(String status) {
        return taskRepository.getTaskByStatus(Status.valueOf(status));
    }

    @Override
    public List<Task> viewAllTasks(long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        return taskRepository.getTasksByUser(user);
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This task was not found"));
    }

    @Override
    public boolean deleteById(long id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public String moveForward(long id) {
        Task task = taskRepository.findById(id).get();
        String status = task.getStatus().toString();
        if (status.equals(Status.PENDING)) {
            task.setStatus(Status.IN_PROGRESS);
        } else if (status.equals("IN_PROGRESS")) {
            task.setStatus(Status.COMPLETED);
            task.setCompletedAt(LocalDateTime.now());
        } else {
            return "Task is already completed";
        }
        taskRepository.save(task);
        return "Task status updated";
    }

    @Override
    public String moveBackward(long id) {
        Task task = taskRepository.findById(id).get();
        Status status = task.getStatus();
        if (status.equals(Status.IN_PROGRESS)) {
            task.setStatus(Status.PENDING);
        } else {
            return "Task is already pending";
        }
        taskRepository.save(task);
        return "Task status updated";
    }

}
