package shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchatDto {
    private Long id;
    private LocalDate date;
    private SrcType srcType;
    private Long fournisseurId;
    private String cin;
    private Double total;
    private Double avance;
    private Double reste;
    private List<AchatItemDto> items;
}

