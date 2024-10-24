package homejava.project.home.spring_pokedex.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "abilities")
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
@NoArgsConstructor
public class Ability extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String name;
    @Column(nullable = false, length = 255)
    private String description;

    public Ability(UUID id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}

