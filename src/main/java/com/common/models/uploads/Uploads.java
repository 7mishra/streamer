package com.common.models.uploads;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "uploads") // Maps to the 'uploads' table in your database
public class Uploads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Correct for MySQL's AUTO_INCREMENT
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "identifier", unique = true, nullable = false)
    private String identifier; // Unique identifier for the uploaded file (e.g., UUID)

    @Column(name = "uploaded_by", nullable = false)
    private String uploadedBy; // User ID or username of the uploader

    @Column(name = "upload_status", nullable = false)
    private String uploadStatus; // e.g., "PENDING", "PROCESSING", "READY", "FAILED"

    @Column(name = "is_hidden", nullable = false)
    private Boolean isHidden; // Maps to TINYINT(1) in MySQL

    @Column(name = "data_path_link", nullable = false, length = 2048)
    private String dataPathLink; // URL or path to the main processed file

    @Column(name = "thumbnail_link", length = 2048)
    private String thumbnailLink; // URL or path to the thumbnail, can be null

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted; // Maps to TINYINT(1) in MySQL (for soft deletion)

    @CreationTimestamp // Automatically sets the creation timestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatically updates the timestamp on entity modification
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Constructors
    public Uploads() {
        // Default constructor for JPA
    }

    public Uploads(String name, String identifier, String uploadedBy, String uploadStatus,
                       Boolean isHidden, String dataPathLink, String thumbnailLink, Boolean isDeleted) {
        this.name = name;
        this.identifier = identifier;
        this.uploadedBy = uploadedBy;
        this.uploadStatus = uploadStatus;
        this.isHidden = isHidden;
        this.dataPathLink = dataPathLink;
        this.thumbnailLink = thumbnailLink;
        this.isDeleted = isDeleted;
    }

    // Getters and Setters (omitted for brevity, assume standard getters/setters)
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
    public String getUploadStatus() { return uploadStatus; }
    public void setUploadStatus(String uploadStatus) { this.uploadStatus = uploadStatus; }
    public Boolean getIsHidden() { return isHidden; }
    public void setIsHidden(Boolean hidden) { isHidden = hidden; }
    public String getDataPathLink() { return dataPathLink; }
    public void setDataPathLink(String dataPathLink) { this.dataPathLink = dataPathLink; }
    public String getThumbnailLink() { return thumbnailLink; }
    public void setThumbnailLink(String thumbnailLink) { this.thumbnailLink = thumbnailLink; }
    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean deleted) { isDeleted = deleted; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", uploadedBy='" + uploadedBy + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", isHidden=" + isHidden +
                ", dataPathLink='" + dataPathLink + '\'' +
                ", thumbnailLink=" + thumbnailLink +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
