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
@Table(name = "credits_fournisseurs")
public class CreditFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fournisseurId;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Type type; // ACHAT ou PAIEMENT

    private Long referenceId;
    private String description;
    private double montant;
    private double reste;

    @Enumerated(EnumType.STRING)
    private Statut statut; // ACTIF ou PAYE

    public enum Type {
        ACHAT, PAIEMENT
    }

    public enum Statut {
        ACTIF, PAYE
    }
}

