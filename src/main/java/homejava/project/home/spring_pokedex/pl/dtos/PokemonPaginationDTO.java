package homejava.project.home.spring_pokedex.pl.dtos;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.bll.services.impls.PokemonServiceImpl;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PokemonPaginationDTO{

    private int count;
    private int previous;
    private int next;
    private PokemonDetailsDTO pokemonDetailsDTO;

    public PokemonPaginationDTO(int count, int previous, int next, PokemonDetailsDTO pokemonDetailsDTO) {
        this.count = count;
        this.previous = previous;
        this.next = next;
        this.pokemonDetailsDTO = pokemonDetailsDTO;
    }


}
