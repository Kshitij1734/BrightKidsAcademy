package com.brightkids_academy.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service

public class FileServicec {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${file.upload-dir:Images}") // Assuming videos are stored in "Images"
    private String uploadDir;

    public String saveFileToServer(MultipartFile file) {
        Path uploadPath = Paths.get(uploadDir);

        // Ensure the upload directory exists
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                logger.info("Upload directory created at: {}", uploadPath.toAbsolutePath());
            } catch (IOException e) {
                logger.error("Failed to create upload directory", e);
                throw new RuntimeException("Failed to create upload directory", e);
            }
        }

        // Generate a unique file name to avoid collisions
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
            logger.info("File saved successfully: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save file", e);
            throw new RuntimeException("Failed to save file", e);
        }

        // Return the file path relative to the base URL, adjust to match URL pattern
        return fileName; // This should match how you access it in the HTML
    }
	
}
