package shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "paiements_telecom")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementTelecom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String cin;
    private Long clientId;
    private String operateur; // TELECOM, ORANGE, TOPNET, GLOBALNET, OOREDOO
    private Double montant;
    private Double gain;
    private Date date;
}
