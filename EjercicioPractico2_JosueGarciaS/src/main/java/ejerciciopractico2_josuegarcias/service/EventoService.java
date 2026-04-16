package ejerciciopractico2_josuegarcias.service;

import java.time.LocalDate;
import java.util.List;
import ejerciciopractico2_josuegarcias.domain.Evento;

public interface EventoService {
    List<Evento> listarEventos();
    Evento obtenerEvento(Evento evento);
    void guardarEvento(Evento evento);
    void eliminarEvento(Evento evento);
    List<Evento> buscarPorEstado(boolean activo);
    List<Evento> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
    List<Evento> buscarPorNombre(String nombre);
    long contarEventosActivos();
}
