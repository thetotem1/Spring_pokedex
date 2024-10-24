package homejava.project.home.spring_pokedex.dal.repositories;

import homejava.project.home.spring_pokedex.dl.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
}
