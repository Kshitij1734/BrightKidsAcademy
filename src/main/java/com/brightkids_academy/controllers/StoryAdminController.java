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

import com.brightkids_academy.entities.StoryAdmin;
import com.brightkids_academy.repos.StoryRepository;
import com.brightkids_academy.services.FileService;

@RestController
public class StoryAdminController {

    @Autowired
    private StoryRepository repo;

    @Autowired
    private FileService fileService;


    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Server is working!");
    }

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadVideo(@RequestParam("story-title") String title,
                                              @RequestParam("story-file") MultipartFile file) {
        // Check if the uploaded file is a video
        if (file.isEmpty() || !file.getContentType().startsWith("video/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is not a video or is empty");
        }

        try {
            // Handle file saving logic
            String filePath = fileService.saveFileToServer(file);

            // Ensure the filePath reflects the correct directory
            String adjustedFilePath = "Images/" + filePath;

            // Save story details in DB
            StoryAdmin story = new StoryAdmin();
            story.setTitle(title);
            story.setFilePath(adjustedFilePath); // Save the adjusted file path
            repo.save(story);

            return ResponseEntity.ok("Video uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading video: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/stories/{id}")
    public ResponseEntity<String> deleteStory(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.ok("Story deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting story: " + e.getMessage());
        }
    }

    @GetMapping("/api/stories")
    public ResponseEntity<List<StoryAdmin>> getAllStories() {
        try {
            List<StoryAdmin> stories = repo.findAll();
            return ResponseEntity.ok(stories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
