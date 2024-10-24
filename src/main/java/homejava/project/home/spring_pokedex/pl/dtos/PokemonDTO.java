package homejava.project.home.spring_pokedex.pl.dtos;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;

public record PokemonDTO(
        int pokedexId,
        String name,
        PokemonType primaryType,
        PokemonType secondaryType
) {

    public static PokemonDTO fromPokemon(Pokemon p) {
        return new PokemonDTO(
                p.getPokedexId(),
                p.getName(),
                p.getPrimaryType(),
                p.getSecondaryType()
        );
    }
}
