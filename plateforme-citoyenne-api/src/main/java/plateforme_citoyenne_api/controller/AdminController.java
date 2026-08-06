package plateforme_citoyenne_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import plateforme_citoyenne_api.entity.Incident;
import plateforme_citoyenne_api.repository.IncidentRepository;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {


    private final IncidentRepository incidentRepository;


    public AdminController(
            IncidentRepository incidentRepository
    ){
        this.incidentRepository = incidentRepository;
    }



    // Voir tous les incidents
    @GetMapping("/incidents")
    public List<Incident> getIncidents(){

        return incidentRepository.findAll();

    }



    // Modifier le statut d'un incident
    @PutMapping("/incident/{id}/statut")
    public Incident modifierStatut(
            @PathVariable Long id,
            @RequestParam String statut
    ){

        Incident incident =
                incidentRepository.findById(id)
                .orElseThrow();


        incident.setStatut(statut);


        return incidentRepository.save(incident);

    }

}