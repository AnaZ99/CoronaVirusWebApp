package com.example.demo.repository;

import com.example.demo.models.CoronaEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronaEventRepository extends JpaRepository<CoronaEvents,Long> {


}
