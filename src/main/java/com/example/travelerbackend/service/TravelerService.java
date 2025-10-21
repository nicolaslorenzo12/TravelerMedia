package com.example.travelerbackend.service;

import com.example.travelerbackend.dto.TravelerPhotoDTO;
import com.example.travelerbackend.dto.UploadPhotoDTO;
import com.example.travelerbackend.model.Traveler;
import com.example.travelerbackend.repository.TravelerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelerService {

    private final TravelerRepository travelerRepository;

    public TravelerService(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file selected");
        }
    }

    public String uploadPhoto(MultipartFile file) throws IOException {

        validateFile(file);

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

    public List<TravelerPhotoDTO> getAllTravelerPhotos() {
        return travelerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TravelerPhotoDTO convertToDTO(Traveler traveler) {
        String base64Image = Base64.getEncoder().encodeToString(traveler.getPhoto());
        return new TravelerPhotoDTO(traveler.getId(), base64Image, traveler.getPhotoType());
    }
}
