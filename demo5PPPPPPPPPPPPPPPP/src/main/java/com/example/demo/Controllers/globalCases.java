package com.example.demo.Controllers;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class globalCases {

    @GetMapping("/global")
    public String prikaz(Model model, HttpServletRequest request) throws IOException, JSONException {


        return "GlobalCases.html";

    }
}
