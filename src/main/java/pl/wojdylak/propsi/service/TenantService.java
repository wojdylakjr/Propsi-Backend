package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Tenant;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.TenantRepository;

import java.util.HashSet;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Tenant createTenantWithCurrentUsers(HashSet<User> currentUsers) {
        String objectName = "TENANT";
        Tenant tenant = new Tenant(currentUsers);
        tenant.setName(objectName + " " + tenant.getId());
        tenantRepository.save(tenant);
        return tenant;
    }

}
