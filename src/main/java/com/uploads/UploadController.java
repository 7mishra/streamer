package com.uploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
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
        if (this.uploadService.uploadVideo(file, "test")) {
            return new ResponseEntity<>("Video uploaded successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("An error occurred during upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
