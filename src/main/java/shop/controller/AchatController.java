package com.example.shop.controller;

import com.example.shop.service.AchatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.Achat;

import java.util.List;

@RestController
@RequestMapping("/api/achats")
@RequiredArgsConstructor
public class AchatController {

    private final AchatService service;

    @GetMapping
    public List<Achat> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achat> getById(@PathVariable Long id) {
        Achat achat = service.getById(id);
        return achat != null ? ResponseEntity.ok(achat) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Achat add(@RequestBody Achat achat) {
        return service.add(achat);
    }

    @PutMapping("/{id}")
    public Achat update(@PathVariable Long id, @RequestBody Achat achat) {
        achat.setId(id);
        return service.update(achat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/fournisseur/{fournisseurId}")
    public List<Achat> getByFournisseur(@PathVariable Long fournisseurId) {
        return service.getByFournisseur(fournisseurId);
    }

    @PostMapping("/{id}/paiement")
    public void addPaiement(@PathVariable Long id, @RequestParam double montant, @RequestParam(required = false) String description) {
        service.addPaiement(id, montant, description != null ? description : "Paiement de dette");
    }
}
