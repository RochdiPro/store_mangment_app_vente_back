package shop.controller;


import org.springframework.web.bind.annotation.*;
import shop.model.PaiementTelecom;
import shop.service.PaiementTelecomService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements-telecom")
@CrossOrigin(origins = "*")
public class PaiementTelecomController {

    private final PaiementTelecomService service;

    public PaiementTelecomController(PaiementTelecomService service) {
        this.service = service;
    }

    @GetMapping
    public List<PaiementTelecom> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaiementTelecom getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PaiementTelecom add(@RequestBody PaiementTelecom paiement) {
        return service.add(paiement);
    }

    @PutMapping("/{id}")
    public PaiementTelecom update(@PathVariable Long id, @RequestBody PaiementTelecom paiement) {
        return service.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/by-client/{clientId}")
    public List<PaiementTelecom> getByClient(@PathVariable Long clientId) {
        return service.getByClient(clientId);
    }
}
