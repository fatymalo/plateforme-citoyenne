package plateforme_citoyenne_api.service;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Incident;
import plateforme_citoyenne_api.entity.Utilisateur;
import plateforme_citoyenne_api.entity.Vote;
import plateforme_citoyenne_api.repository.IncidentRepository;
import plateforme_citoyenne_api.repository.UtilisateurRepository;
import plateforme_citoyenne_api.repository.VoteRepository;

@Service
public class VoteService {


    private final VoteRepository voteRepository;
    private final IncidentRepository incidentRepository;
    private final UtilisateurRepository utilisateurRepository;


    public VoteService(
            VoteRepository voteRepository,
            IncidentRepository incidentRepository,
            UtilisateurRepository utilisateurRepository
    ) {
        this.voteRepository = voteRepository;
        this.incidentRepository = incidentRepository;
        this.utilisateurRepository = utilisateurRepository;
    }



    public String ajouterVote(Long utilisateurId, Long incidentId) {


        // Vérifier si le citoyen a déjà voté
        if (voteRepository.existsByUtilisateurIdAndIncidentId(
                utilisateurId,
                incidentId
        )) {

            return "Vous avez déjà soutenu ce signalement";
        }


        // Récupérer le citoyen
        Utilisateur utilisateur =
                utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable")
                );


        // Récupérer le signalement
        Incident incident =
                incidentRepository.findById(incidentId)
                .orElseThrow(() ->
                        new RuntimeException("Incident introuvable")
                );


        // Créer le vote
        Vote vote = new Vote();

        vote.setUtilisateur(utilisateur);
        vote.setIncident(incident);


        // Enregistrer le vote
        voteRepository.save(vote);



        // Augmenter le nombre de soutiens
        incident.setNombreVotes(
                incident.getNombreVotes() + 1
        );


        // Sauvegarder l'incident mis à jour
        incidentRepository.save(incident);



        return "Vote enregistré avec succès";
    }



    public long nombreVotes() {

        return voteRepository.count();

    }
}