package ejerciciopractico2_josuegarcias.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ejerciciopractico2_josuegarcias.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndActivoTrue(String email);
    List<Usuario> findByRolNombre(String nombreRol);
}
