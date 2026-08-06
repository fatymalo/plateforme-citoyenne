package plateforme_citoyenne_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plateforme_citoyenne_api.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}