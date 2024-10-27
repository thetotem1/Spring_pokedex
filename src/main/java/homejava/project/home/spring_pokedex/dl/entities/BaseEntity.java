package homejava.project.home.spring_pokedex.dl.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * BaseEntity is an abstract class that serves as a superclass for all entities in the application.
 * It provides common properties such as an identifier and timestamps for creation and updates.
 */
@MappedSuperclass // Indicates that this class is a superclass for JPA entities
@Getter // Generates getter methods for all fields
@ToString // Generates a toString() method for the class
@EqualsAndHashCode // Generates equals() and hashCode() methods for the class
@NoArgsConstructor // Generates a no-arguments constructor
public abstract class BaseEntity {

    @Id // Indicates that this field is the primary key in the entity
    @Setter // Generates a setter method for the id field
    private UUID id; // Unique identifier for the entity

    @CreationTimestamp // Automatically sets the field to the current date/time when the entity is created
    private LocalDateTime createdAt; // Timestamp for when the entity was created

    @UpdateTimestamp // Automatically updates the field to the current date/time when the entity is updated
    private LocalDateTime updatedAt; // Timestamp for when the entity was last updated

    /**
     * Constructor to initialize the entity with a specific UUID.
     *
     * @param id The unique identifier for the entity.
     */
    public BaseEntity(UUID id) {
        this.id = id; // Set the id field to the provided UUID
    }
}
