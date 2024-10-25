package homejava.project.home.spring_pokedex.pl.controllers;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonDTO;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonDetailsDTO;
import homejava.project.home.spring_pokedex.pl.dtos.PokemonPaginationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
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

//        try{
////            PokemonDetailsDTO dto = PokemonDetailsDTO.fromPokemon(pokemonService.findById(id));
////            model.addAttribute("pokemon", dto);
////            return "pokemon/details";
//        } catch (Exception e) {
//            return "error/error404";
//        }
    }



}
