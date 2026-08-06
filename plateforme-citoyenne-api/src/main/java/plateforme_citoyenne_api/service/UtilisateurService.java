package plateforme_citoyenne_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Utilisateur;
import plateforme_citoyenne_api.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    public List<Utilisateur> getAll() {
        return repository.findAll();
    }

    public Optional<Utilisateur> getById(Long id) {
        return repository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
