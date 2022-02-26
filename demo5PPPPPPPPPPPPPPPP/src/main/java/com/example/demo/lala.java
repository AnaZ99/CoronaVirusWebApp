package com.example.demo;

import com.example.demo.Repository.JPAREPOSITORY;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class lala implements NewServiceInterface{
    private final JPAREPOSITORY jparepository;

    public lala(JPAREPOSITORY jparepository) {
        this.jparepository = jparepository;
    }

    @Override
    public ArrayList<CoronaEvents> fi() {
        return (ArrayList<CoronaEvents>) this.jparepository.findAll();
    }
}
