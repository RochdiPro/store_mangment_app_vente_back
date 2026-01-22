package shop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProduitType type; // TELEPHONE, ACCESSOIRE

    private String nom;
    private Double prix;
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    private Vente vente;

    public enum ProduitType { TELEPHONE, ACCESSOIRE }
}

