package shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.model.CreditClient;
import shop.model.PaiementCreditClient;
import shop.service.CreditClientService;

import java.util.List;

@RestController
@RequestMapping("/api/credits-clients")
@RequiredArgsConstructor
public class CreditClientController {

    private final CreditClientService service;

    @GetMapping
    public List<CreditClient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CreditClient getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<CreditClient> getByClient(@PathVariable Long clientId) {
        return service.getByClient(clientId);
    }

    @GetMapping("/client/{clientId}/actif")
    public List<CreditClient> getActiveByClient(@PathVariable Long clientId) {
        return service.getActiveByClient(clientId);
    }

    @PostMapping
    public CreditClient add(@RequestBody CreditClient credit) {
        return service.addCredit(credit);
    }

    @PutMapping("/{id}")
    public CreditClient update(@PathVariable Long id, @RequestBody CreditClient credit) {
        credit.setId(id);
        return service.updateCredit(credit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCredit(id);
    }

    @PostMapping("/{id}/paiement")
    public void addPaiement(@PathVariable Long id,
                            @RequestParam double montant,
                            @RequestParam String description) {
        service.addPaiement(id, montant, description);
    }

    @GetMapping("/{id}/paiements")
    public List<PaiementCreditClient> getPaiementsByCredit(@PathVariable Long id) {
        return service.getPaiementsByCredit(id);
    }
}
