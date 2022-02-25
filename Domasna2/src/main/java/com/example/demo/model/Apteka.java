package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "aptekkaa")
public class Apteka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String ime;
    String adresa;
    String rabotnovreme;
    String lokacija;
    String dejnosti;
    String telefon;
    float lon;
    float lat;

    public Apteka(String ime, String adresa, String rabotnovreme, String lokacija, String dejnosti, String telefon, float lon, float lat) {

        this.ime = ime;
        this.adresa = adresa;
        this.rabotnovreme = rabotnovreme;
        this.lokacija = lokacija;
        this.dejnosti = dejnosti;
        this.telefon = telefon;
        this.lon = lon;
        this.lat = lat;
    }

    public String getRabotnovreme() {
        return rabotnovreme;
    }

    public String getLokacija() {
        return lokacija;
    }

    public String getDejnosti() {
        return dejnosti;
    }

    public String getTelefon() {
        return telefon;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public Apteka() {
    }


}
