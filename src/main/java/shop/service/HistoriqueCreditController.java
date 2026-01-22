package shop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.model.HistoriqueCredit;

import java.util.List;

@RestController
@RequestMapping("/api/historique")
@RequiredArgsConstructor
public class HistoriqueCreditController {

    private final HistoriqueCreditService service;

    @GetMapping
    public List<HistoriqueCredit> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public HistoriqueCredit getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/type/{type}")
    public List<HistoriqueCredit> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    @PostMapping
    public HistoriqueCredit add(@RequestBody HistoriqueCredit entry) {
        return service.add(entry);
    }

    @PutMapping("/{id}")
    public HistoriqueCredit update(@PathVariable Long id, @RequestBody HistoriqueCredit entry) {
        entry.setId(id);
        return service.update(entry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

