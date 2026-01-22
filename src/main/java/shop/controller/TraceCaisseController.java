package shop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.TraceCaisse;
import shop.service.TraceCaisseService;

import java.util.List;

@RestController
@RequestMapping("/api/traces-caisse")
@RequiredArgsConstructor
public class TraceCaisseController {

    private final TraceCaisseService service;

    @GetMapping
    public List<TraceCaisse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraceCaisse> getById(@PathVariable Long id) {
        TraceCaisse trace = service.getById(id);
        return trace != null ? ResponseEntity.ok(trace) : ResponseEntity.notFound().build();
    }

    @GetMapping("/caisse/{caisseId}")
    public List<TraceCaisse> getByCaisse(@PathVariable Long caisseId) {
        return service.getByCaisse(caisseId);
    }

    @PostMapping
    public TraceCaisse add(@RequestBody TraceCaisse trace) {
        return service.add(trace);
    }

    @PostMapping("/add-trace")
    public TraceCaisse addTrace(@RequestBody TraceCaisse trace) {
        return service.addTrace(
                trace.getCaisseId(),
                trace.getAction(),
                trace.getMontant(),
                trace.getSoldeAvant(),
                trace.getSoldeApres(),
                trace.getDescription(),
                trace.getReferenceId(),
                trace.getUserId(),
                trace.getUserName(),
                trace.getBenefice()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @DeleteMapping("/clear")
    public void clear() {
        service.clear();
    }
}
