package com.example.demo.web.Controllers.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jazik")

//ovoj kontroler e namenet za promena na jazikot od makedonski vo angliski i obratno.

public class JazikController {

    @GetMapping("/en")
    public String smenijazik(HttpSession httpServletRequest,Model model)
    {
        httpServletRequest.setAttribute("en",1);
        model.addAttribute("bodyContent","home");

        return "MASTER-PAGE";

    }

    @GetMapping("/mk")
    public String smenijaziks(HttpSession httpServletRequest, Model model)
    {
        httpServletRequest.setAttribute("en",null);
        model.addAttribute("bodyContent","home");

        return "MASTER-PAGE";

    }


}
