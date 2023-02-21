package com.starters.dodu.repository;

import com.starters.dodu.domain.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {

    Optional<Mentee> findById(Long id);
    Optional<Mentee> findByEmail(String email);
}
