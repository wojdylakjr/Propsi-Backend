package pl.wojdylak.propsi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.propsi.model.Tenant;
import pl.wojdylak.propsi.repository.TenantRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TenantResource {
    private final TenantRepository tenantRepository;

    public TenantResource(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }


    @GetMapping("/tenants")
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
