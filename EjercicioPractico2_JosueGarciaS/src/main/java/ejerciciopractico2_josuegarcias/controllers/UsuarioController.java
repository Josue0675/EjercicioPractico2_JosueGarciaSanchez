package ejerciciopractico2_josuegarcias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ejerciciopractico2_josuegarcias.domain.Usuario;
import ejerciciopractico2_josuegarcias.service.CorreoService;
import ejerciciopractico2_josuegarcias.service.RolService;
import ejerciciopractico2_josuegarcias.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final CorreoService correoService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService, CorreoService correoService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.correoService = correoService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuario/listado";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Usuario usuario, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerUsuario(usuario));
        return "usuario/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Usuario usuario, Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "usuario/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario, RedirectAttributes redirect) {
        if (usuario.getId() == null) {
            usuarioService.guardarUsuario(usuario);
            correoService.enviarBienvenida(usuario.getEmail(), usuario.getNombre());
            redirect.addFlashAttribute("exito", "Usuario guardado correctamente");
        } else {
            usuarioService.actualizarUsuario(usuario);
            redirect.addFlashAttribute("exito", "Usuario actualizado correctamente");
        }
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(Usuario usuario, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerUsuario(usuario));
        model.addAttribute("roles", rolService.listarRoles());
        return "usuario/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Usuario usuario, RedirectAttributes redirect) {
        usuarioService.eliminarUsuario(usuario);
        redirect.addFlashAttribute("exito", "Usuario eliminado correctamente");
        return "redirect:/usuario/listado";
    }

    @GetMapping("/registro")
    public String registro(Usuario usuario, Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "auth/registro";
    }

    @PostMapping("/registrar")
    public String registrar(Usuario usuario, RedirectAttributes redirect) {
        usuarioService.guardarUsuario(usuario);
        correoService.enviarBienvenida(usuario.getEmail(), usuario.getNombre());
        redirect.addFlashAttribute("exito", "Cuenta creada correctamente");
        return "redirect:/login";
    }
}
