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
@Table(name = "credits_clients")
public class CreditClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Type type; // VENTE | REPARATION | PAIEMENT

    private Long referenceId;
    private String description;
    private double montant;
    private double reste;

    @Enumerated(EnumType.STRING)
    private Statut statut; // ACTIF | PAYE

    public enum Type {
        VENTE, REPARATION, PAIEMENT
    }

    public enum Statut {
        ACTIF, PAYE
    }
}
