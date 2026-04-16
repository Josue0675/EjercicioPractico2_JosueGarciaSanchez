package ejerciciopractico2_josuegarcias.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ejerciciopractico2_josuegarcias.domain.Rol;
import ejerciciopractico2_josuegarcias.repository.RolRepository;
import ejerciciopractico2_josuegarcias.service.RolService;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepo;

    public RolServiceImpl(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    public List<Rol> listarRoles() { return rolRepo.findAll(); }
    public Rol obtenerRol(Rol rol) { return rolRepo.findById(rol.getId()).orElse(null); }
    public void guardarRol(Rol rol) { rolRepo.save(rol); }
    public void eliminarRol(Rol rol) { rolRepo.deleteById(rol.getId()); }
}
