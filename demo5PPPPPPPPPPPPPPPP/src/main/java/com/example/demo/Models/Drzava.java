package com.example.demo.Models;

import lombok.Data;

@Data
public class Drzava {
    String datum;
    int confirmed;
    int deaths;

    public Drzava( String datum,int confirmed, int deaths) {
this.datum=datum;
        this.confirmed = confirmed;
        this.deaths = deaths;
    }

    public Drzava() {

    }
}
