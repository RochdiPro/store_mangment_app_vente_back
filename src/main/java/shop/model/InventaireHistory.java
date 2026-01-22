package shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventaireHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Type type; // ACCESSOIRE, PIECE

    private Long itemId;
    private String nom;

    private Integer ancienStock;
    private Integer nouveauStock;
    private String raison;
    private String utilisateur;

    public enum Type { ACCESSOIRE, PIECE }
}
