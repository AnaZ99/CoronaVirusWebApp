package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/lokator")

//ovoj kontroler ja otvara stranicata Lokator i isto taka sluzi za prebaruvanje na aptekite
//na mapata na istata stranica, vo odnos na lokacijata na korisnikot gi prikazuva najbliskite apteki do nego.

public class Lokator {

    private final AptekaService aptekaService;

    public Lokator( AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }


    @GetMapping
    public String lokator(HttpSession httpSession,Model model)
    {
        model.addAttribute("bodyContent","Lokator");
        model.addAttribute("lokacii",aptekaService.findallPharmacy());

        return "MASTER-PAGE";
    }

    @GetMapping("/search")
    public String search(HttpServletRequest httpServletRequest, Model model, @RequestParam  String search)
    {


        List<Apteka> lista=aptekaService.findbyC(search);
        if(lista.size()==0)
        {

            String cc=aptekaService.ToUpperCase(search);

            cc=aptekaService.convertCyrilic(cc);

            lista=aptekaService.findbyC(cc);

        }

        model.addAttribute("lokacii",lista);

        model.addAttribute("ZaFokus",true);


        model.addAttribute("prebarano",search);
        System.out.println(aptekaService.findbyC(search));
        model.addAttribute("bodyContent","Lokator");
        return "MASTER-PAGE";
    }

}
