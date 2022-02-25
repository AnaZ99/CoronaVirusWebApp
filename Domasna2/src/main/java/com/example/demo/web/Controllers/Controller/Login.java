package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AuthService;
import com.example.demo.model.Userr;
import com.example.demo.model.exceptions.InvalidUserCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")

//ovoj kontroler sluzi za otvoranje na stranicata za najava na korisnikot i isto taka
//funkcijata login ja regulira istata taa najava.

public class Login {
    private final AuthService authService;

    public Login(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String prikazi(Model model)
    {

        model.addAttribute("bodyContent","Login");
        return "MASTER-PAGE";
    }


    @PostMapping("/logiranje")
    public String login(HttpServletRequest request, Model model) {
        Userr user = null;

        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Wrong password or username");
            model.addAttribute("bodyContent","Login");
            return "MASTER-PAGE";
        }
    }
    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("bodyContent","profile");
        return"MASTER-PAGE";

    }



}
