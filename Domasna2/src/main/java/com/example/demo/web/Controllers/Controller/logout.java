package com.example.demo.web.Controllers.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")

//ovoj kontroler sluzi za odjavuvanje od samata aplikacija.

public class logout {

    @GetMapping
    public String odjavi(HttpSession httpSession)
    {
        httpSession.setAttribute("user",null);

        return "redirect:/home";

    }
}
