package com.example.predProject.controller;

import com.example.predProject.dao.UserDAO;
import com.example.predProject.model.User;
import com.example.predProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printAllUsers(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "users";
    }
    @GetMapping("/{id}")
    public String printOneUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deletePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/users";
    }



}
