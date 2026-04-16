package ejerciciopractico2_josuegarcias.config;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            response.sendRedirect("/usuario/listado");
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ORGANIZADOR"))) {
            response.sendRedirect("/evento/listado");
        } else {
            response.sendRedirect("/");
        }
    }
}
