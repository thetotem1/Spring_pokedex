package homejava.project.home.spring_pokedex.pl.dtos;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;

import java.util.UUID;

/**
 * Data Transfer Object for Pokémon summary information.
 * This DTO is used to transfer basic Pokémon information between the service and the presentation layer.
 *
 * @param id           The unique identifier for the Pokémon.
 * @param pokedexId   The Pokédex ID of the Pokémon.
 * @param name         The name of the Pokémon.
 * @param primaryType  The primary type of the Pokémon.
 * @param secondaryType The secondary type of the Pokémon, if any.
 */
public record PokemonDTO(
        UUID id,
        int pokedexId,
        String name,
        PokemonType primaryType,
        PokemonType secondaryType
) {

    /**
     * Converts a Pokémon entity into a PokémonDTO.
     *
     * @param p The Pokémon entity to convert.
     * @return A new PokemonDTO with the data from the Pokémon entity.
     */
    public static PokemonDTO fromPokemon(Pokemon p) {
        return new PokemonDTO(
                p.getId(),
                p.getPokedexId(),
                p.getName(),
                p.getPrimaryType(),
                p.getSecondaryType()
        );
    }
}
