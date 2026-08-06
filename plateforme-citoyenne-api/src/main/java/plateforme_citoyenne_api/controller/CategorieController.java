
package plateforme_citoyenne_api.controller;

import plateforme_citoyenne_api.entity.Categorie;
import plateforme_citoyenne_api.repository.CategorieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieRepository repository;

    public CategorieController(CategorieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Categorie> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return repository.save(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id,
            @RequestBody Categorie categorie) {
        categorie.setId(id);
        return repository.save(categorie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}