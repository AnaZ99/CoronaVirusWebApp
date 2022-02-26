package com.example.demo.Repository;

import com.example.demo.CoronaEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAREPOSITORY extends JpaRepository<CoronaEvents,Long> {



}
