package shop.controller;

import org.springframework.web.bind.annotation.*;
import shop.model.Fournisseur;
import shop.service.FournisseurService;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@CrossOrigin(origins = "*")
public class FournisseurController {

    private final FournisseurService service;

    public FournisseurController(FournisseurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fournisseur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Fournisseur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Fournisseur add(@RequestBody Fournisseur fournisseur) {
        return service.add(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur update(
            @PathVariable Long id,
            @RequestBody Fournisseur fournisseur) {
        return service.update(id, fournisseur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/name-exists")
    public boolean nameExists(
            @RequestParam String nom,
            @RequestParam(required = false) Long excludeId) {
        return service.nameExists(nom, excludeId);
    }

    @PutMapping("/{id}/solde/{montant}")
    public void updateSolde(
            @PathVariable Long id,
            @PathVariable Double montant) {
        service.updateSolde(id, montant);
    }
}

