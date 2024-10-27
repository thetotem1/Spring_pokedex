package homejava.project.home.spring_pokedex.pl.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Data Transfer Object for paginating Pokémon details.
 * This DTO is used to encapsulate pagination information for Pokémon
 * details along with the specific Pokémon being viewed.
 */
@Data
@RequiredArgsConstructor
public class PokemonPaginationDTO {

    /** The total count of Pokémon available. */
    private int count;

    /** The Pokédex ID of the previous Pokémon in the pagination. */
    private int previous;

    /** The Pokédex ID of the next Pokémon in the pagination. */
    private int next;

    /** The detailed information of the current Pokémon. */
    private PokemonDetailsDTO pokemonDetailsDTO;

    /**
     * Constructs a new PokemonPaginationDTO with specified values.
     *
     * @param count             The total count of Pokémon available.
     * @param previous          The Pokédex ID of the previous Pokémon.
     * @param next              The Pokédex ID of the next Pokémon.
     * @param pokemonDetailsDTO The details of the current Pokémon.
     */
    public PokemonPaginationDTO(int count, int previous, int next, PokemonDetailsDTO pokemonDetailsDTO) {
        this.count = count;
        this.previous = previous;
        this.next = next;
        this.pokemonDetailsDTO = pokemonDetailsDTO;
    }
}
