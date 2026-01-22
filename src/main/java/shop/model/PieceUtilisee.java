package shop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieceUtilisee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pieceId;
    private String nom;
    private Integer quantite;
    private Double prixVente;
    private Double prixAchat;

    @ManyToOne
    @JoinColumn(name = "reparation_id")
    private Reparation reparation;
}
