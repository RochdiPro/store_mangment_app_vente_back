package shop.controller;

import org.springframework.web.bind.annotation.*;
import shop.model.Modele;
import shop.service.ModeleService;

import java.util.List;

@RestController
@RequestMapping("/api/modeles")
@CrossOrigin(origins = "*")
public class ModeleController {

    private final ModeleService service;

    public ModeleController(ModeleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Modele> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Modele getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-marque/{marqueId}")
    public List<Modele> getByMarque(@PathVariable Long marqueId) {
        return service.getByMarque(marqueId);
    }

    @PostMapping
    public Modele add(@RequestBody Modele modele) {
        return service.add(modele);
    }

    @PutMapping("/{id}")
    public Modele update(@PathVariable Long id, @RequestBody Modele modele) {
        return service.update(id, modele);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

