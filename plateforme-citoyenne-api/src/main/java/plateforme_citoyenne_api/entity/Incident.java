package plateforme_citoyenne_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "incidents")
@Data
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titre;


    @Column(length = 1000)
    private String description;


    private String photo;


    private Double latitude;


    private Double longitude;


    private String adresse;


    private String priorite;


    private String statut;


    // Nombre de citoyens qui soutiennent ce signalement
    @Column(nullable = false)
    private Integer nombreVotes = 0;



    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;



    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;



    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServicePublic service;

}
