package homejava.project.home.spring_pokedex.bll.services;

import homejava.project.home.spring_pokedex.bll.services.impls.PokemonServiceImpl;
import homejava.project.home.spring_pokedex.dl.entities.Pokemon;

import java.util.List;

public interface PokemonService{

    List<Pokemon> getAllPokemons();
}
