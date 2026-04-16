package ejerciciopractico2_josuegarcias.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ejerciciopractico2_josuegarcias.domain.Usuario;
import ejerciciopractico2_josuegarcias.repository.UsuarioRepository;
import ejerciciopractico2_josuegarcias.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder codificador;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, PasswordEncoder codificador) {
        this.usuarioRepo = usuarioRepo;
        this.codificador = codificador;
    }

    public List<Usuario> listarUsuarios() { return usuarioRepo.findAll(); }
    public Usuario obtenerUsuario(Usuario usuario) { return usuarioRepo.findById(usuario.getId()).orElse(null); }
    public Optional<Usuario> buscarPorEmail(String email) { return usuarioRepo.findByEmailAndActivoTrue(email); }
    public List<Usuario> buscarPorRol(String nombreRol) { return usuarioRepo.findByRolNombre(nombreRol); }

    public void guardarUsuario(Usuario usuario) {
        usuario.setPassword(codificador.encode(usuario.getPassword()));
        usuarioRepo.save(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        Usuario actual = usuarioRepo.findById(usuario.getId()).orElseThrow();
        actual.setNombre(usuario.getNombre());
        actual.setEmail(usuario.getEmail());
        actual.setRol(usuario.getRol());
        actual.setActivo(usuario.isActivo());
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            actual.setPassword(codificador.encode(usuario.getPassword()));
        }
        usuarioRepo.save(actual);
    }

    public void eliminarUsuario(Usuario usuario) { usuarioRepo.deleteById(usuario.getId()); }
}
