package com.example.imageUpDown.controller;

import com.example.imageUpDown.entity.ImageEntity;
import com.example.imageUpDown.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ImageEntity uploadImage(@RequestBody ImageEntity image) {
        return imageService.storeImage(image);
    }

    @GetMapping("/user/{userId}")
    public List<ImageEntity> getUserImages(@PathVariable Long userId) {
        return imageService.retrieveImagesByUserId(userId);
    }
}
