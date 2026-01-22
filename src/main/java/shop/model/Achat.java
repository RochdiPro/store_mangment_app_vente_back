package shop.model;


 import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SrcType srcType; // FOURNISSEUR, PASSAGER

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    private String cin;
    private Double total;
    private Double avance;
    private Double reste;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
    private List<AchatItem> items;

    public enum SrcType { FOURNISSEUR, PASSAGER }
}
