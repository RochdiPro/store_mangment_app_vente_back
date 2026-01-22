package shop.controller;


import org.springframework.web.bind.annotation.*;
import shop.model.Client;
import shop.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Client add(@RequestBody Client client) {
        return service.add(client);
    }

    @PutMapping("/{id}")
    public Client update(
            @PathVariable Long id,
            @RequestBody Client client) {
        return service.update(id, client);
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

    @PutMapping("/{id}/points/{points}")
    public void updatePoints(
            @PathVariable Long id,
            @PathVariable Integer points) {
        service.updatePoints(id, points);
    }
}

