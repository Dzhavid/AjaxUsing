package net.eldarov.usermanager.controller;

import net.eldarov.usermanager.model.User;
import net.eldarov.usermanager.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String userPage(Model model) {

        return "user";
    }

    @RequestMapping("/users")
    public ModelAndView findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/user-create")
    public ModelAndView createUserForm(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-create");
        return  modelAndView;
    }

    @PostMapping("/user-create")
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("user-delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/user-update/{id}")
    public ModelAndView updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-update");
        return modelAndView;
    }

    @PostMapping("/user-update")
    public ModelAndView updateUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }
}
