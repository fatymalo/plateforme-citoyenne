package plateforme_citoyenne_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import plateforme_citoyenne_api.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {


    List<Incident> findByStatut(String statut);


    List<Incident> findByPriorite(String priorite);


    // Les incidents les plus soutenus en premier
    List<Incident> findAllByOrderByNombreVotesDesc();

}