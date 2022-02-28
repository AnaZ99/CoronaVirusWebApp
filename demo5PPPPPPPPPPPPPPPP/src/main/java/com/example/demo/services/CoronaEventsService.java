package com.example.demo.services;

import com.example.demo.models.CoronaEvents;
import com.example.demo.repository.CoronaEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CoronaEventsService implements NewServiceInterface {
    private final CoronaEventRepository coronaEventRepository;

    public CoronaEventsService(CoronaEventRepository coronaEventRepository) {
        this.coronaEventRepository = coronaEventRepository;
    }

    @Override
    public ArrayList<CoronaEvents> najdi() {
        return (ArrayList<CoronaEvents>) this.coronaEventRepository.findAll();
    }
}
