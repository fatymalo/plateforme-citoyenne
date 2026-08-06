package plateforme_citoyenne_api.controller;

import org.springframework.web.bind.annotation.*;

import plateforme_citoyenne_api.service.VoteService;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin("*")
public class VoteController {


    private final VoteService voteService;


    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    // Ajouter un vote pour un incident
    @PostMapping("/{incidentId}")
    public String voter(
            @PathVariable Long incidentId,
            @RequestParam Long utilisateurId
    ) {

        return voteService.ajouterVote(
                utilisateurId,
                incidentId
        );
    }


    // Nombre total de votes
    @GetMapping("/count")
    public long compterVotes() {

        return voteService.nombreVotes();

    }
}