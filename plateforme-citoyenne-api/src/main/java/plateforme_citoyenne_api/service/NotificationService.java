package plateforme_citoyenne_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import plateforme_citoyenne_api.entity.Notification;
import plateforme_citoyenne_api.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
