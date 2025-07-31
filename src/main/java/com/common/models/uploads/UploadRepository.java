package com.common.models.uploads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UploadRepository extends JpaRepository<Uploads, Long> {

    /**
     * Finds an Upload entity by its unique identifier.
     * This is useful for retrieving a specific upload based on the UUID generated for the file.
     * @param identifier The unique string identifier of the upload.
     * @return An Optional containing the Upload entity if found, or empty if not found.
     */
    Optional<Uploads> findByIdentifier(String identifier);

    /**
     * Finds all Upload entities associated with a specific uploader, excluding deleted ones.
     * @param uploadedBy The user ID or username of the uploader.
     * @return A list of Upload entities.
     */
    List<Uploads> findByUploadedByAndIsDeletedFalse(String uploadedBy);

    /**
     * Finds all Upload entities that are not hidden and not deleted.
     * @return A list of publicly visible and active Upload entities.
     */
    List<Uploads> findByIsHiddenFalseAndIsDeletedFalse();
}
