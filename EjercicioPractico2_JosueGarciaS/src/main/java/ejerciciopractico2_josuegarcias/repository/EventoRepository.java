package ejerciciopractico2_josuegarcias.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ejerciciopractico2_josuegarcias.domain.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByActivo(boolean activo);
    List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    long countByActivoTrue();
}
