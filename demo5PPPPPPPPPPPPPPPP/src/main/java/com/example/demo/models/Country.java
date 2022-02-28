package com.example.demo.models;

import lombok.Data;

@Data
public class Country {
    String datum;
    int confirmed;
    int deaths;

    public Country(String datum, int confirmed, int deaths) {
        this.datum=datum;
        this.confirmed = confirmed;
        this.deaths = deaths;
    }

    public Country() {

    }
}
