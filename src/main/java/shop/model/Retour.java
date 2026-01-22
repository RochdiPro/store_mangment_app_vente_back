package shop.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retour {
    private Long id;
    private Date date;
    private String produitType; // "TELEPHONE" ou "ACCESSOIRE"
    private Long produitId;
    private int quantite;
    private String raison;
    private double montant;
}
