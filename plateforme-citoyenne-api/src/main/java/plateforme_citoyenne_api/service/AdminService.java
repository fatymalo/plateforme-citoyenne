package plateforme_citoyenne_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Incident;
import plateforme_citoyenne_api.repository.IncidentRepository;


@Service
public class AdminService {


    private final IncidentRepository incidentRepository;

    private final NotificationService notificationService;



    public AdminService(
            IncidentRepository incidentRepository,
            NotificationService notificationService
    ) {

        this.incidentRepository = incidentRepository;
        this.notificationService = notificationService;

    }




    // Récupérer tous les incidents
    public List<Incident> getTousLesIncidents() {

        return incidentRepository.findAll();

    }





    // Modifier le statut d'un incident
    public Incident modifierStatut(
            Long incidentId,
            String nouveauStatut
    ) {


        Incident incident =
                incidentRepository.findById(incidentId)
                .orElseThrow(() ->
                        new RuntimeException("Incident introuvable")
                );



        incident.setStatut(nouveauStatut);



        Incident sauvegarde =
                incidentRepository.save(incident);




        // Envoyer notification au citoyen propriétaire
        if (incident.getUtilisateur() != null) {


            notificationService.envoyerNotification(

                    incident.getUtilisateur(),

                    "Mise à jour du signalement",

                    "Votre signalement : "
                    + incident.getTitre()
                    + " est maintenant "
                    + nouveauStatut

            );

        }



        return sauvegarde;

    }





    // Modifier la priorité d'un incident
    public Incident modifierPriorite(
            Long incidentId,
            String nouvellePriorite
    ) {


        Incident incident =
                incidentRepository.findById(incidentId)
                .orElseThrow(() ->
                        new RuntimeException("Incident introuvable")
                );



        incident.setPriorite(nouvellePriorite);



        return incidentRepository.save(incident);

    }

}