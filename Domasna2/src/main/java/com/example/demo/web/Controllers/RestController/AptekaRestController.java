package com.example.demo.web.Controllers.RestController;

import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class AptekaRestController {

    private final AptekaService aptekaService;


    public AptekaRestController(AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }

    @GetMapping("/apteki")
    public List<Apteka> findAll()
    {
        return aptekaService.findallPharmacy();
    }




}
