package homejava.project.home.spring_pokedex.bll.services;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Pokémon entities.
 * Provides methods for CRUD operations and data retrieval.
 */
public interface PokemonService {

    /**
     * Retrieves all Pokémon from the data source.
     *
     * @return A list of all Pokémon entities.
     */
    List<Pokemon> getAllPokemons();

//    /**
//     * Finds a Pokémon by its unique identifier.
//     *
//     * @param id The unique identifier of the Pokémon to find.
//     * @return The Pokémon entity with the specified ID, or null if not found.
//     */
    Pokemon findById(UUID id);

//    /**
//     * Counts the total number of Pokémon in the data source.
//     *
//     * @return The total count of Pokémon.
//     */
    int countPokemons();

//    /**
//     * Finds a Pokémon by its unique Pokédex ID.
//     *
//     * @param pokedexId The unique Pokédex ID of the Pokémon to find.
//     * @return The Pokémon entity with the specified Pokédex ID, or null if not found.
//     */
    Pokemon findByPokedexId(int pokedexId);

//    /**
//     * Finds a Pokémon by its name.
//     *
//     * @param name The name of the Pokémon to find.
//     * @return The Pokémon entity with the specified name, or null if not found.
//     */
    Pokemon findByName(String name);

//    /**
//     * Saves a new Pokémon entity to the data source.
//     * If a Pokémon with the same name already exists, an exception will be thrown.
//     *
//     * @param pokemon The Pokémon entity to save.
//     * @return The saved Pokémon entity.
//     * @throws IllegalArgumentException if a Pokémon with the same name already exists.
//     */
    Pokemon save(Pokemon pokemon);
}
