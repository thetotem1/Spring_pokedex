package homejava.project.home.spring_pokedex.bll.services.impls;

import homejava.project.home.spring_pokedex.bll.services.PokemonService;
import homejava.project.home.spring_pokedex.dal.repositories.PokemonRepository;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon findById(UUID id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow();
        return pokemon;
    }

    @Override
    public int countPokemons() {
        return (int) pokemonRepository.count();
    }

    @Override
    public Pokemon findByPokedexId(int pokedexId) {
        return pokemonRepository.findByPokedexId(pokedexId);
    }
}
