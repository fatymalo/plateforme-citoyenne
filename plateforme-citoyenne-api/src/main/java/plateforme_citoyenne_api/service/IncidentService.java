package plateforme_citoyenne_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Incident;
import plateforme_citoyenne_api.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }


    public List<Incident> getAll() {
        return repository.findAll();
    }


    public Optional<Incident> getById(Long id) {
        return repository.findById(id);
    }


    public Incident save(Incident incident) {
        return repository.save(incident);
    }


    public Incident update(Long id, Incident incident) {

        Optional<Incident> existing = repository.findById(id);

        if (existing.isPresent()) {

            Incident ancien = existing.get();

            ancien.setTitre(incident.getTitre());
            ancien.setDescription(incident.getDescription());
            ancien.setPhoto(incident.getPhoto());
            ancien.setLatitude(incident.getLatitude());
            ancien.setLongitude(incident.getLongitude());
            ancien.setAdresse(incident.getAdresse());
            ancien.setPriorite(incident.getPriorite());
            ancien.setStatut(incident.getStatut());
            ancien.setUtilisateur(incident.getUtilisateur());
            ancien.setCategorie(incident.getCategorie());
            ancien.setService(incident.getService());

            return repository.save(ancien);

        } else {
            throw new RuntimeException("Incident non trouvé avec id : " + id);
        }
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}