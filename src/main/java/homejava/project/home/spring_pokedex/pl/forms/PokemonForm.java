package homejava.project.home.spring_pokedex.pl.forms;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form class used to capture input for creating or updating Pokémon.
 * This class is used to hold data submitted from the user in a form,
 * including validation rules to ensure data integrity.
 */
@Data
@NoArgsConstructor
public class PokemonForm {

    /** The Pokédex ID of the Pokémon. Must be provided. */
    @NotNull(message = "Required field")
    private int pokedexId;

    /** The name of the Pokémon. Must not be blank. */
    @NotBlank(message = "Required field")
    private String name;

    /** The primary type of the Pokémon. Must not be null. */
    @NotNull(message = "Primary type cannot be null")
    private PokemonType primaryType;

    /** The secondary type of the Pokémon. Optional field. */
    private PokemonType secondaryType;

    /** The description of the Pokémon. Optional field. */
    private String description;

    /**
     * Constructs a new PokemonForm with the specified parameters.
     *
     * @param pokedexId       The Pokédex ID of the Pokémon.
     * @param name            The name of the Pokémon.
     * @param primaryType     The primary type of the Pokémon.
     * @param secondaryType   The secondary type of the Pokémon.
     * @param description     A description of the Pokémon.
     */
    public PokemonForm(int pokedexId, String name, PokemonType primaryType, PokemonType secondaryType, String description) {
        this.pokedexId = pokedexId;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.description = description;
    }

    /**
     * Converts this form into a Pokemon entity.
     *
     * @return A new Pokemon entity populated with the data from this form.
     */
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
