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
@Table(name = "trace_caisse")
public class TraceCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long caisseId;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Action action;

    private double montant;
    private double soldeAvant;
    private double soldeApres;
    private Long referenceId;
    private String description;
    private Long userId;
    private String userName;
    private Double benefice;

    public enum Action {
        OUVERTURE,
        FERMETURE,
        VENTE,
        ACHAT,
        REPARATION,
        RETOUR,
        PAIEMENT_TELECOM,
        PAIEMENT_CREDIT_CLIENT,
        PAIEMENT_CREDIT_FOURNISSEUR,
        AJUSTEMENT
    }
}
