package homejava.project.home.spring_pokedex.pl.controllers;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonDTO;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonDetailsDTO;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonPaginationDTO;
import homejava.project.home.spring_pokedex.pl.forms.PokemonForm;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Controller for handling web requests related to Pokémon.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    /**
     * Displays a list of all Pokémon.
     *
     * @param model The model to hold attributes for the view.
     * @return The view name for displaying all Pokémon.
     */
    @GetMapping
    public String getPokemon(Model model) {
        List<PokemonDTO> pokemons = pokemonService.getAllPokemons().stream()
                .map(PokemonDTO::fromPokemon)
                .toList();
        model.addAttribute("pokemons", pokemons);
        return "pokemon/all";
    }

    /**
     * Displays the details of a Pokémon identified by its Pokédex ID.
     *
     * @param pokedexId The Pokédex ID of the Pokémon.
     * @param model The model to hold attributes for the view.
     * @return The view name for displaying Pokémon details, or an error view if not found.
     */
    @GetMapping("/{id}")
    public String getPokemonById(@PathVariable(name = "id") int pokedexId, Model model) {
        try {
            int count = pokemonService.countPokemons();
            PokemonDetailsDTO pokemonDetailsDTO = PokemonDetailsDTO.fromPokemon(pokemonService.findByPokedexId(pokedexId));
            PokemonPaginationDTO pokemonPaginationDTO = new PokemonPaginationDTO(
                    count,
                    pokemonDetailsDTO.pokedexId() == 1 ? count : pokemonDetailsDTO.pokedexId() - 1,
                    pokemonDetailsDTO.pokedexId() == count ? 1 : pokemonDetailsDTO.pokedexId() + 1,
                    pokemonDetailsDTO
            );
            model.addAttribute("pokemon", pokemonPaginationDTO);
            return "pokemon/details";
        } catch (NoSuchElementException e) {
            return "error/error404";
        }
    }

    /**
     * Searches for a Pokémon by name.
     *
     * @param nameInput The name of the Pokémon to search for.
     * @param model The model to hold attributes for the view.
     * @return The view name for displaying Pokémon details, or an error view if not found.
     */
    @GetMapping("/search")
    public String searchPokemons(
            @RequestParam(name = "nameInput") String nameInput,
            Model model
    ) {
        try {
            Pokemon pokemon = pokemonService.findByName(nameInput);
            if (pokemon == null && nameInput.isEmpty()) {
                return "error/error404";
            }

            int count = pokemonService.countPokemons();
            PokemonDetailsDTO pokemonDetailsDTO = PokemonDetailsDTO.fromPokemon(
                    pokemonService.findByPokedexId(pokemon.getPokedexId()));
            PokemonPaginationDTO pokemonPaginationDTO = new PokemonPaginationDTO(
                    count,
                    pokemonDetailsDTO.pokedexId() == 1 ? count : pokemonDetailsDTO.pokedexId() - 1,
                    pokemonDetailsDTO.pokedexId() == count ? 1 : pokemonDetailsDTO.pokedexId() + 1,
                    pokemonDetailsDTO
            );
            model.addAttribute("pokemon", pokemonPaginationDTO);
            return "pokemon/details";
        } catch (NullPointerException e) {
            return "error/error404";
        }
    }

    /**
     * Displays the form for creating a new Pokémon.
     *
     * @param model The model to hold attributes for the view.
     * @return The view name for the Pokémon creation form.
     */
    @GetMapping("/create")
    public String createPokemon(Model model) {
        model.addAttribute("pokemonForm", new PokemonForm());
        model.addAttribute("primaryTypes", PokemonType.values());
        model.addAttribute("secondaryTypes", PokemonType.values());
        return "pokemon/create";
    }

    /**
     * Handles the creation of a new Pokémon.
     *
     * @param pokemonForm The form containing the new Pokémon's data.
     * @param bindingResult The result of the validation process.
     * @param model The model to hold attributes for the view.
     * @return The view name for the created Pokémon's details or the creation form with errors.
     */
    @PostMapping("/create")
    public String createPokemon(
            @Valid @ModelAttribute PokemonForm pokemonForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // Collect and add validation errors to the model
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            model.addAttribute("errors", errors);
            model.addAttribute("pokemonForm", pokemonForm);
            model.addAttribute("primaryTypes", PokemonType.values());
            model.addAttribute("secondaryTypes", PokemonType.values());
            return "pokemon/create";
        }

        // Convert form to Pokémon entity and save it
        Pokemon pokemon = pokemonForm.toPokemon();
        pokemonService.save(pokemon);
        return "redirect:/pokemon/" + pokemon.getPokedexId();
    }
}
