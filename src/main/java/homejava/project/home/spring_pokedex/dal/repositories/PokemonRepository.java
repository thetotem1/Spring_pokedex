package homejava.project.home.spring_pokedex.dal.repositories;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Pokémon entities.
 * This interface extends JpaRepository, providing CRUD operations and query methods for Pokémon.
 */
@Repository // Indicates that this interface is a Spring Data Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {

    /**
     * Finds a Pokémon by its unique Pokédex ID.
     *
     * @param pokedexId The unique Pokédex ID of the Pokémon to find.
     * @return The Pokémon entity with the specified Pokédex ID, or null if not found.
     */
    @Query("SELECT p from Pokemon p where p.pokedexId = :pokedexId")
    Pokemon findByPokedexId(int pokedexId);

    /**
     * Finds a Pokémon by its name.
     *
     * @param name The name of the Pokémon to find.
     * @return The Pokémon entity with the specified name, or null if not found.
     */
    @Query("select p from Pokemon p where p.name = :name")
    Pokemon findByName(String name);

    /**
     * Checks if a Pokémon exists with the specified name (case-insensitive).
     *
     * @param name The name of the Pokémon to check for existence.
     * @return true if a Pokémon with the specified name exists, false otherwise.
     */
    @Query("select count(p) > 0 from Pokemon p where p.name ilike :name")
    boolean existsByName(String name);
}
