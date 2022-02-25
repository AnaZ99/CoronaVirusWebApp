package com.example.demo.web.Controllers.RestController;


import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prebaruvanje")
public class SearchRestController {
    private final AptekaService aptekaService;


    public SearchRestController(AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }


    @GetMapping("/search")
    public  List<Apteka> findbySomething (@RequestParam String c)
    {
        c=aptekaService.convertCyrilic(c);
        c=aptekaService.ToUpperCase(c);
        return aptekaService.findbyC(c);
    }

}
