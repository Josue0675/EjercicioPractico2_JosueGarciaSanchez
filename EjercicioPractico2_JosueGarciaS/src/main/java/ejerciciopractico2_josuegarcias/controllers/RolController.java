package ejerciciopractico2_josuegarcias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ejerciciopractico2_josuegarcias.domain.Rol;
import ejerciciopractico2_josuegarcias.service.RolService;

@Controller
@RequestMapping("/rol")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "rol/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Rol rol) { return "rol/modifica"; }

    @PostMapping("/guardar")
    public String guardar(Rol rol, RedirectAttributes redirect) {
        rolService.guardarRol(rol);
        redirect.addFlashAttribute("exito", "Rol guardado correctamente");
        return "redirect:/rol/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(Rol rol, Model model) {
        model.addAttribute("rol", rolService.obtenerRol(rol));
        return "rol/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Rol rol, RedirectAttributes redirect) {
        rolService.eliminarRol(rol);
        redirect.addFlashAttribute("exito", "Rol eliminado correctamente");
        return "redirect:/rol/listado";
    }
}
