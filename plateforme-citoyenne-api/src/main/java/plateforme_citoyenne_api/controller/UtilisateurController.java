package plateforme_citoyenne_api.controller;

import plateforme_citoyenne_api.entity.Utilisateur;
import plateforme_citoyenne_api.repository.UtilisateurRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/utilisateurs")

public class UtilisateurController {


    private final UtilisateurRepository repository;


    public UtilisateurController(
            UtilisateurRepository repository){

        this.repository = repository;

    }



    @GetMapping
    public List<Utilisateur> getAll(){

        return repository.findAll();

    }



    @PostMapping
    public Utilisateur create(
            @RequestBody Utilisateur utilisateur){

        return repository.save(utilisateur);

    }

}