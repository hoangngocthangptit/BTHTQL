package com.example.javaapi.service;

import com.example.javaapi.dto.UserDto;
import com.example.javaapi.entity.Users;

public interface UserServices {
    Users login(UserDto information);
    boolean register(UserDto information);
}
