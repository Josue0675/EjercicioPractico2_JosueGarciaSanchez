package ejerciciopractico2_josuegarcias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ejerciciopractico2_josuegarcias.domain.Evento;
import ejerciciopractico2_josuegarcias.service.EventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("eventos", eventoService.listarEventos());
        return "evento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Evento evento) { return "evento/modifica"; }

    @PostMapping("/guardar")
    public String guardar(Evento evento, RedirectAttributes redirect) {
        eventoService.guardarEvento(evento);
        redirect.addFlashAttribute("exito", "Evento guardado correctamente");
        return "redirect:/evento/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(Evento evento, Model model) {
        model.addAttribute("evento", eventoService.obtenerEvento(evento));
        return "evento/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Evento evento, RedirectAttributes redirect) {
        eventoService.eliminarEvento(evento);
        redirect.addFlashAttribute("exito", "Evento eliminado correctamente");
        return "redirect:/evento/listado";
    }
}
