package com.example.travelerbackend.dto;

public class TravelerPhotoDTO {
    private Long id;
    private String photoBase64;
    private String photoType;

    public TravelerPhotoDTO(Long id, String photoBase64, String photoType) {
        this.id = id;
        this.photoBase64 = photoBase64;
        this.photoType = photoType;
    }

    public Long getId() { return id; }
    public String getPhotoBase64() { return photoBase64; }
    public String getPhotoType() { return photoType; }
}
