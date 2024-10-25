package homejava.project.home.spring_pokedex.pl.dtos;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;

import java.util.UUID;

public record PokemonDetailsDTO(
        UUID id,
        int pokedexId,
        String name,
        String description,
        PokemonType primaryType,
        PokemonType secondaryType

) {

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
