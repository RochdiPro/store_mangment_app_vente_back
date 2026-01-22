package shop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double prix;
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private ProduitType type = ProduitType.ACCESSOIRE;

    @ManyToOne
    @JoinColumn(name = "achat_id")
    private Achat achat;

    public enum ProduitType { ACCESSOIRE }
}
