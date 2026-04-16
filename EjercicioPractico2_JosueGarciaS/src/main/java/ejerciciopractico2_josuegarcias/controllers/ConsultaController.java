package ejerciciopractico2_josuegarcias.controllers;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ejerciciopractico2_josuegarcias.service.EventoService;
import ejerciciopractico2_josuegarcias.service.UsuarioService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
    private final EventoService eventoService;
    private final UsuarioService usuarioService;

    public ConsultaController(EventoService eventoService, UsuarioService usuarioService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("eventos", eventoService.listarEventos());
        model.addAttribute("conteoActivos", eventoService.contarEventosActivos());
        return "consulta/listado";
    }

    @GetMapping("/estado")
    public String porEstado(@RequestParam boolean activo, Model model) {
        model.addAttribute("eventos", eventoService.buscarPorEstado(activo));
        model.addAttribute("tituloConsulta", "Eventos por estado");
        return "consulta/resultados";
    }

    @GetMapping("/fechas")
    public String porFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin, Model model) {
        model.addAttribute("eventos", eventoService.buscarPorRangoFechas(inicio, fin));
        model.addAttribute("tituloConsulta", "Eventos por rango de fechas");
        return "consulta/resultados";
    }

    @GetMapping("/nombre")
    public String porNombre(@RequestParam String nombre, Model model) {
        model.addAttribute("eventos", eventoService.buscarPorNombre(nombre));
        model.addAttribute("tituloConsulta", "Eventos por nombre");
        return "consulta/resultados";
    }

    @GetMapping("/usuarios-rol")
    public String usuariosPorRol(@RequestParam String rol, Model model) {
        model.addAttribute("usuarios", usuarioService.buscarPorRol(rol));
        model.addAttribute("tituloConsulta", "Usuarios por rol");
        return "consulta/usuarios_resultado";
    }
}
