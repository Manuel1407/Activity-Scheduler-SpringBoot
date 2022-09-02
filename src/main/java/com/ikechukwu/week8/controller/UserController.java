package com.ikechukwu.week8.controller;

import com.ikechukwu.week8.dto.TaskDTO;
import com.ikechukwu.week8.dto.UserDTO;
import com.ikechukwu.week8.model.Task;
import com.ikechukwu.week8.model.User;
import com.ikechukwu.week8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/dashboard")
    public String index(Model model,HttpSession session) {
        if(session.getAttribute("id") == null) return "redirect:/login";
        long id = (long) session.getAttribute("id");
        List<Task> allTasks = userService.viewAllTasks(id);
        model.addAttribute("listTasks", allTasks);
        return "dashboard";
    }

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDetails", new UserDTO());
        return "registration";
    }

    @GetMapping(value = "/signUpSuccess")
    public String showSignUpSuccess(){
        return "signUpSuccess";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("registerDetails") UserDTO userDTO) {
        User registeredUser = userService.registration(userDTO);
        if (registeredUser != null) {
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }
    }

    @GetMapping(value = "/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("userDetails", new UserDTO());
        return "login";
    }

    @PostMapping(value = "/loginAccess")
    public String loginUsers (@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session, Model model) {
        String message = userService.login(username, password);
        if(message.equals("Success")) {
            User user = userService.getUserByUsername(username);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getFullname());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("errorMessage", message);
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/task/{status}")
    public String taskByStatus(@PathVariable(name = "status") String status, Model model) {
        List<Task> allTasksByStatus = userService.viewAllTasksByStatus(status);

        model.addAttribute("taskByStatus", allTasksByStatus);
        return "task_by_status";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name="id") Long id) {
        userService.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/editPage/{id}")
    public String editPage(@PathVariable(name = "id") Long id, Model model) {
        Task task = userService.getTaskById(id);
        model.addAttribute("singleTask", task);
        model.addAttribute("taskBody", new TaskDTO());
        return "edit_task";
    }

    @PostMapping(value="/edit/{id}")
    public String editTask(@PathVariable(name = "id") Long id, @ModelAttribute TaskDTO taskDTO) {
        userService.updateTitleAndDescription(taskDTO, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/viewPage/{id}")
    public String viewPage(@PathVariable(name = "id") Long id, Model model) {
        Task task = userService.getTaskById(id);
        model.addAttribute("task", task);
        return "view_task";
    }

    @GetMapping("/addNewTask")
    public String addTask(Model model) {
        model.addAttribute("newTask", new TaskDTO());
        return "create_task";
    }

    @PostMapping("/addTask")
    public String CreateTask(@ModelAttribute TaskDTO taskDTO,HttpSession session) {
        long id  = (long) session.getAttribute("id");
        userService.createTask(taskDTO,id);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping(value = "/arrow-right/{id}")
    public String moveStatusForward(@PathVariable(name = "id") long id){
        userService.moveForward(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/arrow-left/{id}")
    public String moveStatusBackward(@PathVariable(name = "id") long id){
        userService.moveBackward(id);
        return "redirect:/dashboard";
    }

}
