package ejerciciopractico2_josuegarcias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ejerciciopractico2_josuegarcias.service.EventoService;

@Controller
public class InicioController {
    private final EventoService eventoService;

    public InicioController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("eventos", eventoService.listarEventos());
        return "index";
    }
}
