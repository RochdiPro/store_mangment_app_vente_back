package shop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "historique_credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // VENTE | ACHAT | REPARATION | PAIEMENT
    private Long referenceId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String description;
    private double montant;
}

