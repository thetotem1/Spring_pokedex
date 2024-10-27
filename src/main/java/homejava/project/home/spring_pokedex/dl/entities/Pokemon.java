package homejava.project.home.spring_pokedex.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import homejava.project.home.spring_pokedex.dl.enums.PokemonType;
import java.util.UUID;

@Entity
@Getter @Setter
@ToString(callSuper=true) @EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Pokemon extends BaseEntity {

    @Column(unique=true, nullable=false)
    private int pokedexId;
    @Column(unique = true, nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PokemonType primaryType;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private PokemonType secondaryType;

    public Pokemon(UUID id, int pokedexId, String name, String description, PokemonType primaryType, PokemonType secondaryType) {
        super(id);
        this.pokedexId = pokedexId;
        this.name = name;
        this.description = description;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    public Pokemon(int pokedexId, String name, String description, PokemonType primaryType, PokemonType secondaryType) {
        super(UUID.randomUUID());
        this.pokedexId = pokedexId;
        this.name = name;
        this.description = description;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }
}
