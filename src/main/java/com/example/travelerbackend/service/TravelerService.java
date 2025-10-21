package com.example.travelerbackend.service;


import com.example.travelerbackend.model.Traveler;
import com.example.travelerbackend.repository.TravelerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class TravelerService {

    private final TravelerRepository travelerRepository;

    public TravelerService(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    public String uploadPhoto(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("No file selected");
        }

        Traveler traveler = new Traveler();
        traveler.setPhoto(file.getBytes());
        traveler.setPhotoType(file.getContentType());
        travelerRepository.save(traveler);

        return "/api/photo/" + traveler.getId();
    }

    public Traveler getTravelerPhoto(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    public List<Traveler> getAllTravelers() {
        return travelerRepository.findAll();
    }
}