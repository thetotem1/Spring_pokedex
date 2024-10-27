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

@Controller
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    public String getPokemon(Model model) {
        List<PokemonDTO> pokemons = pokemonService.getAllPokemons().stream().map(PokemonDTO::fromPokemon).toList();
        model.addAttribute("pokemons", pokemons);
        return "pokemon/all";
    }

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

//        try{
////            PokemonDetailsDTO dto = PokemonDetailsDTO.fromPokemon(pokemonService.findById(id));
////            model.addAttribute("pokemon", dto);
////            return "pokemon/details";
//        } catch (Exception e) {
//            return "error/error404";
//        }
    }


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

    @GetMapping("/create")
    public String createPokemon(Model model) {

        model.addAttribute("pokemonForm", new PokemonForm());
        model.addAttribute("primaryTypes", PokemonType.values());
        model.addAttribute("secondaryTypes", PokemonType.values());
        return "pokemon/create";
    }

    @PostMapping("/create")
    public String createArticle(
            @Valid @ModelAttribute PokemonForm pokemonForm,
            BindingResult bindingResult,
            Model model
    ) {

        if(bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            model.addAttribute("errors", errors);
            model.addAttribute("pokemonForm", pokemonForm);
            model.addAttribute("primaryTypes", PokemonType.values());
            model.addAttribute("secondaryTypes", PokemonType.values());
            return "pokemon/create";
        }

        Pokemon pokemon = pokemonForm.toPokemon();
        pokemon.setPokedexId(pokemonForm.getPokedexId());
        pokemon.setName(pokemonForm.getName());
        pokemon.setPrimaryType(pokemonForm.getPrimaryType());
        pokemon.setSecondaryType(pokemonForm.getSecondaryType());

        pokemonService.save(pokemon);
        return "redirect:/details";

    }


}
