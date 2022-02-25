package com.example.demo.Service;

import com.example.demo.model.Userr;

public interface AuthService {

    Userr login(String username, String password);
    Userr register(String username, String password, String repeatPassword, String name, String surname,String telefon,String pol,String email);
}

