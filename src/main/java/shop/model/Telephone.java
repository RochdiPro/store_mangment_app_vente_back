package shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "telephones")
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private String codeBarre;
    private String imei1;
    private String imei2;
    private String numeroSerie;
    private double prixAchat;
    private double prixVente;

    @Enumerated(EnumType.STRING)
    private EtatTelephone etat; // ACHAT, RETOUR, OCCASION

    @Enumerated(EnumType.STRING)
    private SrcType srcType; // FOURNISSEUR, PASSAGER

    private Long srcId;
    private String cin;
    private boolean vendu;
    private Long clientId;
    private String clientNom;

    @Enumerated(EnumType.STRING)
    private VenteType venteType; // CLIENT, PASSAGER

    private String acheteurCin;

    public enum EtatTelephone { ACHAT, RETOUR, OCCASION }
    public enum SrcType { FOURNISSEUR, PASSAGER }
    public enum VenteType { CLIENT, PASSAGER }
}
