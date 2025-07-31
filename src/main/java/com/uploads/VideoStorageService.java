package com.uploads;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class VideoStorageService {

    private final Path fileStorageLocation;

    public VideoStorageService() {
        String uploadDir = "uploads/videos";
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public List<String> storeVideo(InputStream inputStream, String originalFilename, String contentType) throws IOException {
        String fileName = StringUtils.cleanPath(originalFilename);

        // Generate a unique file name to prevent overwrites and provide security
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(dotIndex);
        }
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        try {
            if (uniqueFileName.contains("..")) {
                throw new IOException("Filename contains invalid path sequence " + uniqueFileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);

            // Use Files.copy for efficient streaming directly from InputStream to File
            // StandardCopyOption.REPLACE_EXISTING ensures if a file with the same name exists, it's replaced
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return List.of(uniqueFileName, targetLocation.toString());
        } catch (IOException ex) {
            throw new IOException("Could not store file " + originalFilename + ". Please try again!", ex);
        } finally {
            // Ensure the input stream is closed, although Spring typically handles this for MultipartFile
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
