package com.spring.blogitbackend.services;

import com.cloudinary.Cloudinary;
import com.spring.blogitbackend.exceptions.ImageUploadException;
import com.spring.blogitbackend.payloads.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileService {
    private final Cloudinary cloudinary;

    public FileService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public ApiResponse uploadImage(MultipartFile img) {
        try {
            Map data = this.cloudinary.uploader().upload(img.getBytes(), Map.of());
            String url = data.get("url").toString();
            return new ApiResponse(url,true);
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image to Cloudinary");
        }
    }
}
