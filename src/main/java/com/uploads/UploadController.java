package com.uploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/videos")
public class UploadController {

    private final VideoStorageService videoStorageService;

    @Autowired
    public UploadController(VideoStorageService videoStorageService) {
        this.videoStorageService = videoStorageService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/health")
    public String health() {
        return "Upload Server Up and Running";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            String uniqueFileName = videoStorageService.storeVideo(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            return new ResponseEntity<>("Video uploaded successfully! Stored as: " + uniqueFileName, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
