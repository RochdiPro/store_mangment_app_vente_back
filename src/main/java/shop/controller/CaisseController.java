package shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.Caisse;
import shop.service.CaisseService;

import java.util.List;

@RestController
@RequestMapping("/api/caisses")
public class CaisseController {

    private final CaisseService caisseService;

    public CaisseController(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @GetMapping
    public List<Caisse> getAll() {
        return caisseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caisse> getById(@PathVariable Long id) {
        Caisse caisse = caisseService.getById(id);
        if (caisse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(caisse);
    }

    @PostMapping("/open")
    public ResponseEntity<Caisse> openCaisse(@RequestParam double soldeInitial) {
        Caisse caisse = caisseService.openCaisse(soldeInitial);
        return ResponseEntity.ok(caisse);
    }

    @PostMapping("/close/{id}")
    public ResponseEntity<Void> closeCaisse(@PathVariable Long id) {
        try {
            caisseService.closeCaisse(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/adjust")
    public ResponseEntity<Void> ajusterSolde(
            @PathVariable Long id,
            @RequestParam double montant,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) Double benefice
    ) {
        try {
            caisseService.ajusterSolde(id, montant, description, action, referenceId, benefice);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caisse> update(@PathVariable Long id, @RequestBody Caisse caisse) {
        caisse.setId(id);
        caisseService.update(caisse);
        return ResponseEntity.ok(caisse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        caisseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
