package com.example.travelerbackend.repository;

import com.example.travelerbackend.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {}
