package shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieceRechange {
    private Long id;
    private String nom;
    private int quantite;
    private double prixAchat;
    private double prixVente;
    private String description;
    private Integer stockCritique;   // optionnel
    private Integer stockAlerte;     // optionnel
    private String codeBarre;
    private Integer nouvelleQuantite;  // optionnel
    private String raisonCorrection;   // optionnel
}

