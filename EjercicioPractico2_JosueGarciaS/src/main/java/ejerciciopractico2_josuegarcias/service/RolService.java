package ejerciciopractico2_josuegarcias.service;

import java.util.List;
import ejerciciopractico2_josuegarcias.domain.Rol;

public interface RolService {
    List<Rol> listarRoles();
    Rol obtenerRol(Rol rol);
    void guardarRol(Rol rol);
    void eliminarRol(Rol rol);
}
