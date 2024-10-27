package homejava.project.home.spring_pokedex.dal.repositories;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {

    @Query("SELECT p from Pokemon p where p.pokedexId = :pokedexId")
    Pokemon findByPokedexId(int pokedexId);

    @Query("select p from Pokemon p where p.name = :name")
    Pokemon findByName(String name);

    @Query("select count(p) > 0 from Pokemon p where p.name ilike :name")
    boolean existsByName(String name);

}
