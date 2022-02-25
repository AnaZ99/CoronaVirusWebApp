package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AptekaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/home",""})

//so ovoj kontroler se otvora pocetnata stranica na aplikacijata na jazikot na koj shto go ima izbrano
//samiot korisnik, kako i listanjeto na site apteki na stratana Apteki.

public class AptekaController {
    private final AptekaService aptekaService;


    public AptekaController(AptekaService aptekaService) {
        this.aptekaService = aptekaService;

    }

    /**
     *
     * @param model
     * @param search
     * @param session
     * @param req
     * @return
     */


    @GetMapping
    public String HomePage(Model model,@RequestParam(required = false) String search,HttpSession session,HttpServletRequest req)
    {

        if(session.getAttribute("en")!=null)
        {
            return "redirect:/jazik/en";

    }
        else{
            return "redirect:/jazik/mk";

        }


        }


    /**
     *
     * @param model
     * @param search
     * @param request
     * @return
     */

    @GetMapping("/apteki")
    public String SiteApteki(Model model, @RequestParam(required = false) String search, HttpSession request)
    {

            request.setAttribute("koj",null);
        model.addAttribute("bodyContent","SiteApteki");

            model.addAttribute("apteki",aptekaService.findallPharmacy());


            return "MASTER-PAGE";}


    }





