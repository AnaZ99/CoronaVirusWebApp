package com.example.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

public class CoronaEvents {
    @Id
    @GeneratedValue
    Long id;
    String date;
    String url;
    String title;
    String image;
    String location;
    String lat;
    String lon;
    String countryCode;
    String contextualText;

    public CoronaEvents(String url, String title, String image, String location, String lat, String lon, String countryCode, String contextualText) {
        this.url = url;
        this.title = title;
        this.image = image;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.countryCode = countryCode;
        this.contextualText = contextualText;
    }
    public  void CoronaEventss(String url,String date, String title, String image, String location, String lat, String lon, String countryCode, String contextualText) {
        this.url = url;
        this.title = title;
        this.image = image;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.countryCode = countryCode;
        this.contextualText = contextualText;
        this.date=date;
    }

    public CoronaEvents() {

    }
}
