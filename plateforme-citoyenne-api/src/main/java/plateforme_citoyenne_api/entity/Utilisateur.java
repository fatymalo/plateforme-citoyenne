package plateforme_citoyenne_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "utilisateurs")
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;


    private String prenom;


    @Column(unique = true)
    private String email;


    private String motDePasse;


    private String telephone;


    @Column(nullable = false)
    private String role = "CITOYEN";

}