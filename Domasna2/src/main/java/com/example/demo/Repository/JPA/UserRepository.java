package com.example.demo.Repository.JPA;

import com.example.demo.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//interface do bazata na podatoci za USERS
public interface UserRepository extends JpaRepository<Userr, String> {
    Optional<Userr> findByUsernameAndPassword(String username, String password);
    Optional<Userr> findByUsername(String username);
}
