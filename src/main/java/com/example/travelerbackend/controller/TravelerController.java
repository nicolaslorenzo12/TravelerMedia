package com.example.travelerbackend.controller;

import com.example.travelerbackend.dto.TravelerPhotoDTO;
import com.example.travelerbackend.model.Traveler;
import com.example.travelerbackend.service.TravelerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TravelerController {

    private final TravelerService travelerService;

    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    /**
     * Uploads a photo.
     * The file is received as a MultipartFile, that gives me convenient access to its bytes and
     * content type without manual parsing.
     * Declaring throws IOException signals that reading the file might fail,
     * letting global handler deal with it gracefully, if i do not handle it somewhere,
     * the code will not even start.
     */
    @PostMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        String photoUrl = travelerService.uploadPhoto(file);
        return ResponseEntity.ok(photoUrl);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Traveler traveler = travelerService.getTravelerPhoto(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(traveler.getPhotoType()));
        return new ResponseEntity<>(traveler.getPhoto(), headers, HttpStatus.OK);
    }

    @GetMapping("/photos")
    public List<TravelerPhotoDTO> getAllPhotos() {
        return travelerService.getAllTravelerPhotos();
    }
}
