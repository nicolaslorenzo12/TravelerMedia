package com.example.travelerbackend.service;

import com.example.travelerbackend.dto.TravelerPhotoDTO;
import com.example.travelerbackend.model.Photo;
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

    public void uploadPhoto(MultipartFile file) throws IOException {

        validateFile(file);

        Photo traveler = new Photo();
        traveler.setPhotoBytes(file.getBytes());
        traveler.setPhotoType(file.getContentType());
        travelerRepository.save(traveler);
    }

    public Photo getTravelerPhoto(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    public List<TravelerPhotoDTO> getAllTravelerPhotos() {
        return travelerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TravelerPhotoDTO convertToDTO(Photo traveler) {
        String base64Image = Base64.getEncoder().encodeToString(traveler.getPhotoBytes());
        return new TravelerPhotoDTO(traveler.getId(), base64Image, traveler.getPhotoType());
    }
}
