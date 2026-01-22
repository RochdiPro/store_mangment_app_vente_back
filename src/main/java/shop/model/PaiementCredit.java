package shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long creditId;

    @Enumerated(EnumType.STRING)
    private CreditType creditType; // CLIENT, FOURNISSEUR

    private LocalDate date;
    private Double montant;
    private String description;

    public enum CreditType { CLIENT, FOURNISSEUR }
}
