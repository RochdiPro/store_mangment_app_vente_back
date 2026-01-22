package shop.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SrcType srcType; // CLIENT, PASSAGER

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String cin;
    private Double sousTotal;
    private Double remise;
    private Double total;
    private Double avance;
    private Double reste;
    private Double benefice;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
    private List<VenteItem> items;

    public enum SrcType { CLIENT, PASSAGER }
}

