package com.example.predProject.service;


import com.example.predProject.model.User;

import java.util.List;

public interface UserService {
    public List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
}
