package com.example.travelerbackend.repository;

import com.example.travelerbackend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends JpaRepository<Photo, Long> {}
