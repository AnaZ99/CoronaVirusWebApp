package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Uusers")
public class Userr {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    private String telefon;

    private String pol;

    private String email;

    public Userr(String username, String password, String name, String surname, String telefon, String pol,String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telefon = telefon;
        this.pol = pol;
        this.email=email;
    }

    public Userr() {
    }


}