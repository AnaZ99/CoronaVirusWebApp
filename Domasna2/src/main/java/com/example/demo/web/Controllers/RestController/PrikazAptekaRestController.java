package com.example.demo.web.Controllers.RestController;

import com.example.demo.Service.AptekaService;
import com.example.demo.model.Apteka;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("showApi")
public class PrikazAptekaRestController {
    private final AptekaService aptekaService;


    public PrikazAptekaRestController(AptekaService aptekaService) {
        this.aptekaService = aptekaService;
    }


    @GetMapping("/prikaz/{id}")
    public Apteka show(Model model, HttpServletRequest request, @PathVariable Long id)
    {
        return aptekaService.findbyId(id);


    }
}
