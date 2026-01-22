package shop.controller;


import org.springframework.web.bind.annotation.*;
import shop.model.Accessoire;
import shop.service.AccessoireService;

import java.util.List;

@RestController
@RequestMapping("/api/accessoires")
@CrossOrigin(origins = "*")
public class AccessoireController {

    private final AccessoireService service;

    public AccessoireController(AccessoireService service) {
        this.service = service;
    }

    @GetMapping
    public List<Accessoire> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Accessoire getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Accessoire add(@RequestBody Accessoire accessoire) {
        return service.add(accessoire);
    }

    @PutMapping("/{id}")
    public Accessoire update(
            @PathVariable Long id,
            @RequestBody Accessoire accessoire) {
        return service.update(id, accessoire);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/quantite/{quantite}")
    public Accessoire updateQuantite(
            @PathVariable Long id,
            @PathVariable Integer quantite) {
        return service.updateQuantite(id, quantite);
    }

    @GetMapping("/en-stock")
    public List<Accessoire> getEnStock() {
        return service.getEnStock();
    }
}
