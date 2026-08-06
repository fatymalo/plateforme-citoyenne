package plateforme_citoyenne_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import plateforme_citoyenne_api.entity.Incident;
import plateforme_citoyenne_api.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin("*")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }


    // Afficher tous les incidents
    @GetMapping
    public List<Incident> getAll() {
        return service.getAll();
    }


   // Ajouter un incident


@PostMapping
public Incident create(@RequestBody Incident incident) {

    try {
        System.out.println("===== INCIDENT REÇU =====");
        System.out.println("Titre : " + incident.getTitre());
        System.out.println("Categorie : " + incident.getCategorie());
        System.out.println("Service : " + incident.getService());
        System.out.println("Utilisateur : " + incident.getUtilisateur());

        return service.save(incident);

   
 } catch (Exception e) {
        e.printStackTrace();   // <-- très important
        throw e;
    }



}




  @GetMapping("/{id}")
public Incident getById(@PathVariable Long id) {

    return service.getById(id)
            .orElseThrow(() -> new RuntimeException("Incident non trouvé"));
}


    // Modifier un incident
    @PutMapping("/{id}")
    public Incident update(
            @PathVariable Long id,
            @RequestBody Incident incident) {

        return service.update(id, incident);
    }


    // Supprimer un incident
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
