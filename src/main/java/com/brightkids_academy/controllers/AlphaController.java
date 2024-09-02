//package com.brightkids_academy.controllers;
//
//public class AlphaController {
//
//}

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

import com.brightkids_academy.entities.AlphaAdmin;
//import com.brightkids_academy.entities.AlphaAdmin; // Update the entity name to AlphaAdmin
import com.brightkids_academy.repos.AlphaRepository;
//import com.brightkids_academy.repos.ImageRepository; // Update the repository name to AlphaRepository
import com.brightkids_academy.services.FileService;

@RestController
public class AlphaController {

    @Autowired
    private AlphaRepository repo; // Updated repository

    @Autowired
    private FileService fileService;

    @PostMapping("/upload-alpha")
    public ResponseEntity<String> uploadAlpha(@RequestParam("alpha-title") String title,
                                              @RequestParam("alpha-file") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is not an image or is empty");
        }

        try {
            String filePath = fileService.saveFileToServer(file);
            String adjustedFilePath = "uploads/" + filePath;

            AlphaAdmin alpha = new AlphaAdmin(); // Updated entity
            alpha.setTitle(title);
            alpha.setFilePath(adjustedFilePath);
            repo.save(alpha);

            return ResponseEntity.ok("Alpha uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading alpha: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/alphas/{id}")
    public ResponseEntity<String> deleteAlpha(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.ok("Alpha deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting alpha: " + e.getMessage());
        }
    }

    @GetMapping("/api/alphas")
    public ResponseEntity<List<AlphaAdmin>> getAllAlphas() {
        try {
            List<AlphaAdmin> alphas = repo.findAll();
            return ResponseEntity.ok(alphas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
