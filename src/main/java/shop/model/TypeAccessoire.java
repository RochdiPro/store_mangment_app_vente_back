package shop.model;
import jakarta.persistence.*;
import lombok.*;

import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAccessoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
}