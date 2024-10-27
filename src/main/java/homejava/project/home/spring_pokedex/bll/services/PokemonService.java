package homejava.project.home.spring_pokedex.bll.services;

import homejava.project.home.spring_pokedex.bll.services.impls.PokemonServiceImpl;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;

import java.util.List;
import java.util.UUID;

public interface PokemonService{

    List<Pokemon> getAllPokemons();
    Pokemon findById(UUID id);
    int countPokemons();
    Pokemon findByPokedexId(int pokedexId);
    Pokemon findByName(String name);
    Pokemon save(Pokemon pokemon);

}
