package shop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProduitType produitType; // TELEPHONE, ACCESSOIRE

    private Long produitId;
    private Integer quantite;
    private Integer seuilCritique;

    public enum ProduitType { TELEPHONE, ACCESSOIRE }
}