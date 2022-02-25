package com.example.demo;

import lombok.Data;

@Data
public class CoronaEvents {
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
}
