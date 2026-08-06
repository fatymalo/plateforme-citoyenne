package plateforme_citoyenne_api.service;

import plateforme_citoyenne_api.entity.ServicePublic;
import plateforme_citoyenne_api.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAdminService {

    private final ServiceRepository repository;

    public ServiceAdminService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<ServicePublic> getAll() {
        return repository.findAll();
    }
}
