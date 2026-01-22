package shop.model;

import com.app.shop.client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String marque;
    private String modele;
    private String telephone;
    private String defaut;
    private String description;
    private Double prix;
    private Double avance;
    private Double reste;
    private Double remise;
    private Double benefice;

    @Enumerated(EnumType.STRING)
    private Etat etat; // EN_ATTENTE, ACCEPTEE, EN_REPARATION, TERMINE, LIVREE, ANNULER

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL)
    private List<PieceUtilisee> piecesUtilisees;

    @ElementCollection
    @CollectionTable(name = "test_entree", joinColumns = @JoinColumn(name = "reparation_id"))
    @MapKeyColumn(name = "test_id")
    @Column(name = "valeur")
    private Map<Long, Boolean> testEntree;

    @ElementCollection
    @CollectionTable(name = "test_sortie", joinColumns = @JoinColumn(name = "reparation_id"))
    @MapKeyColumn(name = "test_id")
    @Column(name = "valeur")
    private Map<Long, Boolean> testSortie;

    public enum Etat { EN_ATTENTE, ACCEPTEE, EN_REPARATION, TERMINE, LIVREE, ANNULER }
}