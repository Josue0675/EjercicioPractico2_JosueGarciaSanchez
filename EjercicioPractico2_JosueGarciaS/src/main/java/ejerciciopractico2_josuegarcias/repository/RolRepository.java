package ejerciciopractico2_josuegarcias.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ejerciciopractico2_josuegarcias.domain.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
