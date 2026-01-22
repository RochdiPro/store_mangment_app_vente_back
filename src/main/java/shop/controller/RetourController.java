package shop.controller;



import org.springframework.web.bind.annotation.*;
import shop.model.Retour;
import shop.service.RetourService;

import java.util.List;

@RestController
@RequestMapping("/api/retours")
@CrossOrigin(origins = "*")
public class RetourController {

    private final RetourService service;

    public RetourController(RetourService service) {
        this.service = service;
    }

    // === Toutes les retours ===
    @GetMapping
    public List<Retour> getAll() {
        return service.getAll();
    }

    // === Retour par ID ===
    @GetMapping("/{id}")
    public Retour getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    // === Ajouter un retour ===
    @PostMapping
    public Retour add(@RequestBody Retour retour) {
        return service.add(retour);
    }

    // === Supprimer un retour ===
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

