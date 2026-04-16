package ejerciciopractico2_josuegarcias.service;

import java.util.List;
import java.util.Optional;
import ejerciciopractico2_josuegarcias.domain.Usuario;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuario(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> buscarPorRol(String nombreRol);
    void guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
}
