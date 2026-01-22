package shop.controller;


import org.springframework.web.bind.annotation.*;
import shop.model.PieceRechange;
import shop.service.PieceRechangeService;

import java.util.List;

@RestController
@RequestMapping("/api/pieces-rechange")
@CrossOrigin(origins = "*")
public class PieceRechangeController {

    private final PieceRechangeService service;

    public PieceRechangeController(PieceRechangeService service) {
        this.service = service;
    }

    @GetMapping
    public List<PieceRechange> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PieceRechange getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public PieceRechange add(@RequestBody PieceRechange piece) {
        return service.add(piece);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody PieceRechange piece) {
        piece.setId(id);
        return service.update(piece);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/{id}/decrement-stock")
    public boolean decrementStock(@PathVariable Long id, @RequestParam int quantite) {
        return service.decrementStock(id, quantite);
    }

    @PostMapping("/{id}/increment-stock")
    public boolean incrementStock(@PathVariable Long id, @RequestParam int quantite) {
        return service.incrementStock(id, quantite);
    }

    @GetMapping("/rupture")
    public List<PieceRechange> getPiecesEnRupture() {
        return service.getPiecesEnRupture();
    }

    @GetMapping("/faible-stock")
    public List<PieceRechange> getPiecesFaibleStock(@RequestParam(defaultValue = "5") int seuil) {
        return service.getPiecesFaibleStock(seuil);
    }

    @PostMapping("/{id}/update-quantite")
    public boolean updateQuantite(@PathVariable Long id, @RequestParam int nouvelleQuantite) {
        return service.updateQuantite(id, nouvelleQuantite);
    }
}

