package com.example.rafflebuddy.controller;

import com.example.rafflebuddy.model.Raffle;
import com.example.rafflebuddy.model.RaffleImage;
import com.example.rafflebuddy.service.RaffleImageService;
import com.example.rafflebuddy.service.RaffleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/raffle-images")
public class RaffleImageController {

    final RaffleService raffleService;
    final RaffleImageService raffleImageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public RaffleImageController(RaffleService raffleService, RaffleImageService raffleImageService) {
        this.raffleService = raffleService;
        this.raffleImageService = raffleImageService;
    }

    @PostMapping("/{raffleId}")
    public ResponseEntity<?> uploadImage(@PathVariable Long raffleId, @RequestParam("src") MultipartFile[] files, @RequestParam("description") String description) {
        try {
            Path dirPath = Paths.get(System.getProperty("user.dir") + uploadDir + "/" + raffleId);
            File dir = new File(dirPath.toString());

            // Create the directory if it doesn't exist
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Could not create upload directory");
                }
            }

            for(MultipartFile file : files) {
                // Get the original file name and create the target file
                String fileName = file.getOriginalFilename();
                Path filePath = dirPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                // Bind to its raffle
                Raffle raffle = raffleService.getRaffleById(raffleId);

                RaffleImage raffleImage = new RaffleImage();
                raffleImage.setSrc(filePath.toString());
                raffleImage.setDescription(description);
                raffleImage.setRaffle(raffle);
                raffleImageService.saveRaffleImage(raffleImage);
            }

            // Save the file information and description to the database (placeholder)
            // For now, you can just return the file path and description.
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("File upload failed");
    }
    }
}
