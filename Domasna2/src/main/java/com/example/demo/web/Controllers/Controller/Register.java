package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AuthService;
import com.example.demo.model.exceptions.InvalidArgumentsException;
import com.example.demo.model.exceptions.PasswordsDoNotMatchException;
import com.example.demo.model.exceptions.UsernameAlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")

//ovoj kontroler ja otvara stranicata za registracija na korisnik i funkcijata
//register go kontrolira registriranjeto na samiot korisnik i dokolku toa e ne uspeshno
//go redirektira povtorno kon istata stranica za registracija, namesto kon stranicata
//za najava. Dokolku vnesenoto korisnicko ime vekje postoi se pojavuva poraka za greshka
//i moznost za izbiranje na novo korisnicko ime od strana na korisnikot.

public class Register {
    private final AuthService authService;

    public Register(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping

    public String prikazi(HttpServletRequest request, @RequestParam(required = false) String error, Model model)
    {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","Register");
        return "MASTER-PAGE";


    }


    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String telefon,
                           @RequestParam String pol,
                           @RequestParam String email
    ) {
        try{
            this.authService.register(username, password, repeatedPassword, name, surname,telefon,pol,email);
            return "redirect:/login";
        } catch (InvalidArgumentsException | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=Korisnickoto ime vekje postoi";
        }
    }


}
