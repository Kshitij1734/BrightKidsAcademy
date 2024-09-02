package com.brightkids_academy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brightkids_academy.entities.ImageAdmin;
import com.brightkids_academy.repos.ImageRepository;
import com.brightkids_academy.services.FileService;

@RestController
public class ImageAdminController {

    @Autowired
    private ImageRepository repo;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("image-title") String title,
                                              @RequestParam("image-file") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is not an image or is empty");
        }

        try {
            String filePath = fileService.saveFileToServer(file);
            String adjustedFilePath = "uploads/" + filePath;

            ImageAdmin image = new ImageAdmin();
            image.setTitle(title);
            image.setFilePath(adjustedFilePath);
            repo.save(image);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/images/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image: " + e.getMessage());
        }
    }

    @GetMapping("/api/images")
    public ResponseEntity<List<ImageAdmin>> getAllImages() {
        try {
            List<ImageAdmin> images = repo.findAll();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
