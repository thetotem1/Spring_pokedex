package homejava.project.home.spring_pokedex.pl.controllers;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
