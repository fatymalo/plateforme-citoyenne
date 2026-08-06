package plateforme_citoyenne_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import plateforme_citoyenne_api.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

}