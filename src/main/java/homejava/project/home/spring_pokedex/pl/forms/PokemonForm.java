package homejava.project.home.spring_pokedex.pl.forms;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonForm {

    @NotNull(message = "Required field")
    private int pokedexId;
    @NotBlank(message = "Required field")
    private String name;

    private PokemonType primaryType;

    private PokemonType secondaryType;

    private String description;

    public PokemonForm(int pokedexId, String name, PokemonType primaryType, PokemonType secondaryType, String description) {
        this.pokedexId = pokedexId;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.description = description;
    }

    public Pokemon toPokemon() {
        return new Pokemon(
                this.pokedexId,
                this.name,
                this.description,
                this.primaryType,
                this.secondaryType
        );
    }
}
