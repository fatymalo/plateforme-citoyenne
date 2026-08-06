package plateforme_citoyenne_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import plateforme_citoyenne_api.entity.Notification;
import plateforme_citoyenne_api.service.NotificationService;
import plateforme_citoyenne_api.repository.NotificationRepository;


@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {


    private final NotificationRepository notificationRepository;


    private final NotificationService notificationService;


    public NotificationController(
            NotificationRepository notificationRepository,
            NotificationService notificationService
    ) {

        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;

    }



    // Récupérer les notifications d'un utilisateur
    @GetMapping("/{utilisateurId}")
    public List<Notification> getNotifications(
            @PathVariable Long utilisateurId
    ) {

        return notificationRepository
                .findByUtilisateurId(utilisateurId);

    }



    // Créer une notification
    @PostMapping
    public Notification createNotification(
            @RequestBody Notification notification
    ) {

        return notificationService.save(notification);

    }



    // Supprimer une notification
    @DeleteMapping("/{id}")
    public void deleteNotification(
            @PathVariable Long id
    ) {

        notificationService.delete(id);

    }

}