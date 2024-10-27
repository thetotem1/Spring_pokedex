package homejava.project.home.spring_pokedex.pl.dtos;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;

import java.util.UUID;

/**
 * Data Transfer Object for Pokémon details.
 * This DTO is used to transfer Pokémon details between the service and the presentation layer.
 *
 * @param id           The unique identifier for the Pokémon.
 * @param pokedexId   The Pokédex ID of the Pokémon.
 * @param name         The name of the Pokémon.
 * @param description  A description of the Pokémon.
 * @param primaryType  The primary type of the Pokémon.
 * @param secondaryType The secondary type of the Pokémon, if any.
 */
public record PokemonDetailsDTO(
        UUID id,
        int pokedexId,
        String name,
        String description,
        PokemonType primaryType,
        PokemonType secondaryType
) {

    /**
     * Converts a Pokémon entity into a PokémonDetailsDTO.
     *
     * @param p The Pokémon entity to convert.
     * @return A new PokemonDetailsDTO with the data from the Pokémon entity.
     */
    public static PokemonDetailsDTO fromPokemon(Pokemon p) {
        return new PokemonDetailsDTO(
                p.getId(),
                p.getPokedexId(),
                p.getName(),
                p.getDescription(),
                p.getPrimaryType(),
                p.getSecondaryType()
        );
    }
}
