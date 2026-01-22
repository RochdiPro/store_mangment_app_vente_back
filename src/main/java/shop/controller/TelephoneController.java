package shop.controller;

import org.springframework.web.bind.annotation.*;
import shop.model.Telephone;
import shop.service.TelephoneService;

import java.util.List;

@RestController
@RequestMapping("/api/telephones")
@CrossOrigin(origins = "*")
public class TelephoneController {

    private final TelephoneService service;

    public TelephoneController(TelephoneService service) {
        this.service = service;
    }

    @GetMapping
    public List<Telephone> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Telephone getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public Telephone add(@RequestBody Telephone telephone) {
        return service.add(telephone);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Telephone telephone) {
        telephone.setId(id);
        return service.update(telephone);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/en-stock")
    public List<Telephone> getEnStock() {
        return service.getEnStock();
    }

    @PostMapping("/{id}/vendu")
    public boolean markAsVendu(@PathVariable Long id,
                               @RequestParam Long clientId,
                               @RequestParam(required = false) String clientNom,
                               @RequestParam(required = false) String venteType,
                               @RequestParam(required = false) String acheteurCin) {
        return service.markAsVendu(id, clientId, clientNom, venteType, acheteurCin);
    }

    @GetMapping("/search")
    public List<Telephone> searchByImei(@RequestParam String imei) {
        return service.searchByImei(imei);
    }

    @GetMapping("/by-client/{clientId}")
    public List<Telephone> getByClient(@PathVariable Long clientId) {
        return service.getByClient(clientId);
    }

    @GetMapping("/by-etat/{etat}")
    public List<Telephone> getByEtat(@PathVariable String etat) {
        return service.getByEtat(etat);
    }
}
