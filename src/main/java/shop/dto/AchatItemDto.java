package shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.model.Achat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchatItemDto {
    private Long produitId;
    private ProduitType type;
    private String nom;
    private Double prix;
    private Integer quantite;
}