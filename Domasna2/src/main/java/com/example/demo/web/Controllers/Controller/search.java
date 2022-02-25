package com.example.demo.web.Controllers.Controller;

import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/search")

//ovoj kontroler sluzi za prebaruvanje na bilo shto vo samata aplikacija
//nasekade vo nea. (delot Search koj se naogja vo headerot na samata aplikacija i
//e dostapen na sekoja od stranicite vo aplikacijata).


public class search {
    private final AptekaService aptekaService;

    public search(AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }


    //funkcijata pokategorija ovozmozuva na korisnikot prebaruvanje na bilo shto so
    //garantiranje na siguren rezultat duri i po vnesena kratenka (primer sk)
    //istata pri autocomplete mu dava moznosti za izbor na korisnikot.
    @GetMapping
    public String pokategorija(Model model,HttpServletRequest request)
    {

        String d=request.getParameter("search");
        request.setAttribute("koj",d);


       List<Apteka> lista= aptekaService.findbyC(d);

       if(lista.size()==0)
       {

           String   cc=  aptekaService.ToUpperCase(d);

           cc=aptekaService.convertCyrilic(cc);
           System.out.println(cc);

           lista=aptekaService.findbyC(cc);

       }

        model.addAttribute("apteki",lista);
        model.addAttribute("tekst",d);
        model.addAttribute("bodyContent","SiteApteki");

        return "MASTER-PAGE";
    }
}
