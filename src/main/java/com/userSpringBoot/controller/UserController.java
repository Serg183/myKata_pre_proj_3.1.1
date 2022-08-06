package com.userSpringBoot.controller;


import com.userSpringBoot.model.User;
import com.userSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("users")
    public String listUser(Model model) {
        model.addAttribute("users", userService.listUser());

        return "users";
    }

    @GetMapping("create")
    public String createUserForm(User user) {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            if(user != null) {
                userService.removeUser(id);
            }
            return "redirect:/users";
        } catch (Exception e) {
            return "user not found";
        }
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }

}
