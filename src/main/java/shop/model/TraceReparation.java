package shop.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceReparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reparation_id")
    private Reparation reparation;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Etat ancienEtat;

    @Enumerated(EnumType.STRING)
    private Etat nouvelEtat;

    private String description;

    public enum Etat {
        EN_ATTENTE,
        ACCEPTEE,
        EN_REPARATION,
        TERMINE,
        LIVREE,
        ANNULER
    }
}

