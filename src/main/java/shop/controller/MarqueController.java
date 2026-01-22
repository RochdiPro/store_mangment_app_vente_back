package shop.controller;

import org.springframework.web.bind.annotation.*;
import shop.model.Marque;
import shop.service.MarqueService;

import java.util.List;

@RestController
@RequestMapping("/api/marques")
@CrossOrigin(origins = "*")
public class MarqueController {

    private final MarqueService service;

    public MarqueController(MarqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<Marque> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Marque getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Marque add(@RequestBody Marque marque) {
        return service.add(marque);
    }

    @PutMapping("/{id}")
    public Marque update(@PathVariable Long id, @RequestBody Marque marque) {
        return service.update(id, marque);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
