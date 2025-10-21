package com.example.travelerbackend.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadPhotoDTO {

    private MultipartFile file;

    public UploadPhotoDTO() {}

    public UploadPhotoDTO(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
