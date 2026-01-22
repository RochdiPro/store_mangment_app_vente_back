package shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.model.DefautStandard;
import shop.service.DefautStandardService;

import java.util.List;

@RestController
@RequestMapping("/api/defauts")
@RequiredArgsConstructor
public class DefautStandardController {

    private final DefautStandardService service;

    @GetMapping
    public List<DefautStandard> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DefautStandard getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public DefautStandard add(@RequestBody DefautStandard defaut) {
        return service.add(defaut);
    }

    @PutMapping("/{id}")
    public DefautStandard update(@PathVariable Long id, @RequestBody DefautStandard defaut) {
        defaut.setId(id);
        return service.update(defaut);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
