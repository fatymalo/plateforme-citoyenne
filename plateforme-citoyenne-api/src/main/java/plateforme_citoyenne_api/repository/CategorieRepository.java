package plateforme_citoyenne_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plateforme_citoyenne_api.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
