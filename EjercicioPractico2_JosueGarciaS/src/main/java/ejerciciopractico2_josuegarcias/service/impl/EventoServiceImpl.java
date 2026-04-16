package ejerciciopractico2_josuegarcias.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import ejerciciopractico2_josuegarcias.domain.Evento;
import ejerciciopractico2_josuegarcias.repository.EventoRepository;
import ejerciciopractico2_josuegarcias.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepo;

    public EventoServiceImpl(EventoRepository eventoRepo) {
        this.eventoRepo = eventoRepo;
    }

    public List<Evento> listarEventos() { return eventoRepo.findAll(); }
    public Evento obtenerEvento(Evento evento) { return eventoRepo.findById(evento.getId()).orElse(null); }
    public void guardarEvento(Evento evento) { eventoRepo.save(evento); }
    public void eliminarEvento(Evento evento) { eventoRepo.deleteById(evento.getId()); }
    public List<Evento> buscarPorEstado(boolean activo) { return eventoRepo.findByActivo(activo); }
    public List<Evento> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) { return eventoRepo.findByFechaBetween(inicio, fin); }
    public List<Evento> buscarPorNombre(String nombre) { return eventoRepo.findByNombreContainingIgnoreCase(nombre); }
    public long contarEventosActivos() { return eventoRepo.countByActivoTrue(); }
}
