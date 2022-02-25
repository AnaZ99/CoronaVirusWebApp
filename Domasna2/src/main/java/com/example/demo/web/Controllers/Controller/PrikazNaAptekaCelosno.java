package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/show")

//ovoj kontroler gi dava celosnite informacii za odredena apteka, za koja shto korisnikot
//saka da doznae povekje informacii.

public class PrikazNaAptekaCelosno {
    private final AptekaService aptekaService;


    public PrikazNaAptekaCelosno(AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }

    /**
     *
     * @param model
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/prikaz/{id}")
    public String show(Model model, HttpServletRequest request, @PathVariable Long id)
    {
        Apteka a= aptekaService.findbyId(id);
        float lon=a.getLon();
                float lat=a.getLat();
                model.addAttribute("so4",lat);
        model.addAttribute("so2",lon);


        request.getSession().setAttribute("apteka",a);
        model.addAttribute("bodyContent","showApteka");
        return "MASTER-PAGE";

    }


}
