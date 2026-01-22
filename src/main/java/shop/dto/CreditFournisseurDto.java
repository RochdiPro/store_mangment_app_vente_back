package shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditFournisseurDto {
    private Long id;
    private Long fournisseurId;
    private LocalDate date;
    private String type;
    private Long referenceId;
    private String description;
    private Double montant;
    private Double reste;
    private String statut;
}