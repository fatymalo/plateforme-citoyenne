package plateforme_citoyenne_api.controller;

import plateforme_citoyenne_api.entity.ServicePublic;
import plateforme_citoyenne_api.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository repository;

    public ServiceController(ServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ServicePublic> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ServicePublic getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public ServicePublic create(@RequestBody ServicePublic service) {
        return repository.save(service);
    }

    @PutMapping("/{id}")
    public ServicePublic update(@PathVariable Long id,
                                @RequestBody ServicePublic service) {
        service.setId(id);
        return repository.save(service);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}