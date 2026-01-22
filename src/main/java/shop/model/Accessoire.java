package shop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


   public class Accessoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private String codeBarre;

    private Double prixAchat;
    private Double prixVente;

    private Integer quantite;
    private Integer stockCritique;
    private Integer stockAlerte;
}
