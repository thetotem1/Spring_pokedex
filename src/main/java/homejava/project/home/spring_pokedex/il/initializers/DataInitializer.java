package homejava.project.home.spring_pokedex.il.initializers;

import homejava.project.home.spring_pokedex.dal.repositories.PokemonRepository;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * DataInitializer is a component that initializes the database with
 * predefined Pokémon data when the application starts.
 * Implements CommandLineRunner to execute code after the application context is loaded.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    // Repository for managing Pokémon entities
    private final PokemonRepository pokemonRepository;

    /**
     * This method runs after the Spring application has started.
     * It checks if there are any Pokémon in the repository,
     * and if not, populates it with a predefined list of Pokémon.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    @Override
    public void run(String... args) {
        // Check if there are any Pokémon in the repository
        if (pokemonRepository.count() == 0) {
            // Create a list of Pokémon to be added to the repository
            List<Pokemon> pokemons = List.of(
                    new Pokemon(UUID.randomUUID(), 1, "Bulbasaur", "A seed Pokémon that can grow into a large plant.", PokemonType.GRASS, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 2, "Ivysaur", "A fully grown Bulbasaur with a blooming flower.", PokemonType.GRASS, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 3, "Venusaur", "The final form of Bulbasaur, large and powerful.", PokemonType.GRASS, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 4, "Charmander", "A small lizard Pokémon that breathes fire.", PokemonType.FIRE, null),
                    new Pokemon(UUID.randomUUID(), 5, "Charmeleon", "The intermediate stage of Charmander, more aggressive.", PokemonType.FIRE, null),
                    new Pokemon(UUID.randomUUID(), 6, "Charizard", "A mighty fire-breathing dragon with the ability to fly.", PokemonType.FIRE, PokemonType.FLYING),
                    new Pokemon(UUID.randomUUID(), 7, "Squirtle", "A tiny turtle Pokémon that uses water to attack.", PokemonType.WATER, null),
                    new Pokemon(UUID.randomUUID(), 8, "Wartortle", "An evolved form of Squirtle with larger water-based abilities.", PokemonType.WATER, null),
                    new Pokemon(UUID.randomUUID(), 9, "Blastoise", "A fully evolved turtle Pokémon equipped with water cannons.", PokemonType.WATER, null),
                    new Pokemon(UUID.randomUUID(), 10, "Caterpie", "A small bug Pokémon with a voracious appetite for leaves.", PokemonType.BUG, null),
                    new Pokemon(UUID.randomUUID(), 11, "Metapod", "A cocoon stage before Caterpie evolves into Butterfree.", PokemonType.BUG, null),
                    new Pokemon(UUID.randomUUID(), 12, "Butterfree", "A butterfly Pokémon with large wings that scatter dust.", PokemonType.BUG, PokemonType.FLYING),
                    new Pokemon(UUID.randomUUID(), 13, "Weedle", "A bug Pokémon with a poisonous sting.", PokemonType.BUG, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 14, "Kakuna", "The cocoon form of Weedle, it is nearly immobile.", PokemonType.BUG, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 15, "Beedrill", "An aggressive bee Pokémon with powerful stingers.", PokemonType.BUG, PokemonType.POISON),
                    new Pokemon(UUID.randomUUID(), 16, "Pidgey", "A common bird Pokémon with basic flying abilities.", PokemonType.NORMAL, PokemonType.FLYING),
                    new Pokemon(UUID.randomUUID(), 17, "Pidgeotto", "A larger and stronger form of Pidgey, known for its speed.", PokemonType.NORMAL, PokemonType.FLYING),
                    new Pokemon(UUID.randomUUID(), 18, "Pidgeot", "The final evolution of Pidgey, capable of flying at high speeds.", PokemonType.NORMAL, PokemonType.FLYING),
                    new Pokemon(UUID.randomUUID(), 19, "Rattata", "A small rodent Pokémon known for its speed and sharp teeth.", PokemonType.NORMAL, null),
                    new Pokemon(UUID.randomUUID(), 20, "Raticate", "The evolved form of Rattata with powerful biting abilities.", PokemonType.NORMAL, null)
            );

            // Attempt to save the predefined Pokémon to the repository
            try {
                pokemonRepository.saveAll(pokemons);
            } catch (Exception e) {
                // Handle any exceptions that occur during the save operation
                System.err.println("Failed to save Pokémon data to the repository: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
