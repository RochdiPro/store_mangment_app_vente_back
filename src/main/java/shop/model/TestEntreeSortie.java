package shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests_entree_sortie")
public class TestEntreeSortie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int ordre;

    public enum Type {
        ENTREE,
        SORTIE
    }
}
