package homejava.project.home.spring_pokedex.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import java.util.UUID;

/**
 * Pokemon class represents a Pokémon entity in the database.
 * It inherits common properties from the BaseEntity class.
 */
@Entity // Marks this class as a JPA entity
@Getter // Generates getter methods for all fields
@Setter // Generates setter methods for all fields
@ToString(callSuper = true) // Generates toString() method, including superclass fields
@EqualsAndHashCode(callSuper = true) // Generates equals() and hashCode() methods, including superclass fields
@NoArgsConstructor // Generates a no-argument constructor
public class Pokemon extends BaseEntity {

    @Column(unique = true, nullable = false) // Specifies that the column is unique and cannot be null
    private int pokedexId; // Unique identifier for the Pokémon in the Pokédex

    @Column(unique = true, nullable = false, length = 255) // Specifies that the name must be unique, not null, and has a max length of 255
    private String name; // Name of the Pokémon

    @Column(nullable = false, length = 255) // Specifies that the description cannot be null and has a max length of 255
    private String description; // Description of the Pokémon

    @Column(nullable = false) // Specifies that the primary type cannot be null
    @Enumerated(EnumType.STRING) // Indicates that the enum should be persisted as a string in the database
    private PokemonType primaryType; // Primary type of the Pokémon (e.g., Fire, Water)

    @Column(nullable = true) // Specifies that the secondary type can be null
    @Enumerated(EnumType.STRING) // Indicates that the enum should be persisted as a string in the database
    private PokemonType secondaryType; // Secondary type of the Pokémon (if any)

    /**
     * Constructor to create a Pokémon instance with a specific UUID.
     *
     * @param id           The unique identifier for the Pokémon.
     * @param pokedexId   The unique Pokédex ID for the Pokémon.
     * @param name        The name of the Pokémon.
     * @param description The description of the Pokémon.
     * @param primaryType The primary type of the Pokémon.
     * @param secondaryType The secondary type of the Pokémon (optional).
     */
    public Pokemon(UUID id, int pokedexId, String name, String description, PokemonType primaryType, PokemonType secondaryType) {
        super(id); // Call to superclass constructor to set the UUID
        this.pokedexId = pokedexId; // Set the Pokédex ID
        this.name = name; // Set the Pokémon name
        this.description = description; // Set the description
        this.primaryType = primaryType; // Set the primary type
        this.secondaryType = secondaryType; // Set the secondary type
    }

    /**
     * Constructor to create a Pokémon instance with a randomly generated UUID.
     *
     * @param pokedexId   The unique Pokédex ID for the Pokémon.
     * @param name        The name of the Pokémon.
     * @param description The description of the Pokémon.
     * @param primaryType The primary type of the Pokémon.
     * @param secondaryType The secondary type of the Pokémon (optional).
     */
    public Pokemon(int pokedexId, String name, String description, PokemonType primaryType, PokemonType secondaryType) {
        super(UUID.randomUUID()); // Generate a random UUID for the Pokémon
        this.pokedexId = pokedexId; // Set the Pokédex ID
        this.name = name; // Set the Pokémon name
        this.description = description; // Set the description
        this.primaryType = primaryType; // Set the primary type
        this.secondaryType = secondaryType; // Set the secondary type
    }
}
