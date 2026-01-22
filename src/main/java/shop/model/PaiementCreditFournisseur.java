package shop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paiements_credits_fournisseurs")
public class PaiementCreditFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long creditId;

    @Enumerated(EnumType.STRING)
    private CreditType creditType; // CLIENT ou FOURNISSEUR

    private Date date;
    private double montant;
    private String description;

    public enum CreditType {
        CLIENT, FOURNISSEUR
    }
}
