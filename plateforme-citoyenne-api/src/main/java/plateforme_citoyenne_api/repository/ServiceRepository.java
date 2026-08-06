package plateforme_citoyenne_api.repository;

import plateforme_citoyenne_api.entity.ServicePublic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<ServicePublic, Long> {

}