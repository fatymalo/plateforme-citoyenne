package plateforme_citoyenne_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Categorie;
import plateforme_citoyenne_api.repository.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository repository;

    public CategorieService(CategorieRepository repository) {
        this.repository = repository;
    }

    public List<Categorie> getAll() {
        return repository.findAll();
    }

    public Categorie save(Categorie categorie) {
        return repository.save(categorie);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
