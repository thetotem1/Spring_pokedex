package homejava.project.home.spring_pokedex.bll.services.impls;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.dal.repositories.PokemonRepository;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the PokemonService interface.
 * This class provides methods for managing Pokémon data and interacts with the Pokémon repository.
 */
@Service // Indicates that this class is a Spring service component
@RequiredArgsConstructor // Automatically generates a constructor for required fields
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository; // Injected Pokémon repository for data access

    /**
     * Retrieves all Pokémon from the repository.
     *
     * @return A list of all Pokémon entities.
     */
    @Override
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll(); // Uses repository to fetch all Pokémon
    }

//    /**
//     * Finds a Pokémon by its unique ID.
//     *
//     * @param id The unique identifier of the Pokémon to find.
//     * @return The Pokémon entity with the specified ID.
//     * @throws NoSuchElementException if no Pokémon with the specified ID exists.
//     */
    @Override
    public Pokemon findById(UUID id) {
        // Uses repository to find Pokémon by ID, throwing an exception if not found
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow();
        return pokemon;
    }

//    /**
//     * Counts the total number of Pokémon in the repository.
//     *
//     * @return The total count of Pokémon.
//     */
    @Override
    public int countPokemons() {
        return (int) pokemonRepository.count(); // Retrieves the count from the repository
    }

//    /**
//     * Finds a Pokémon by its unique Pokédex ID.
//     *
//     * @param pokedexId The unique Pokédex ID of the Pokémon to find.
//     * @return The Pokémon entity with the specified Pokédex ID, or null if not found.
//     */
    @Override
    public Pokemon findByPokedexId(int pokedexId) {
        return pokemonRepository.findByPokedexId(pokedexId); // Uses repository to find by Pokédex ID
    }

//    /**
//     * Finds a Pokémon by its name.
//     *
//     * @param name The name of the Pokémon to find.
//     * @return The Pokémon entity with the specified name, or null if not found.
//     */
    @Override
    public Pokemon findByName(String name) {
        return pokemonRepository.findByName(name); // Uses repository to find by name
    }

    /**
     * Saves a new Pokémon entity to the repository.
     * If the Pokémon name already exists, an exception is thrown.
     *
     * @param pokemon The Pokémon entity to save.
     * @return The saved Pokémon entity.
     * @throws IllegalArgumentException if a Pokémon with the same name already exists.
     */
    @Override
    public Pokemon save(Pokemon pokemon) {
        // Check if Pokémon with the same name exists
        if (pokemonRepository.existsByName(pokemon.getName())) {
            throw new IllegalArgumentException("Designation already exists"); // Throw exception if exists
        }
        pokemon.setId(UUID.randomUUID()); // Set a new unique ID for the Pokémon

        return pokemonRepository.save(pokemon); // Save the Pokémon to the repository
    }
}
