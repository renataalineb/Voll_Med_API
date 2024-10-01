package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var tokenJWT = recuperarToken(request);


        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Autorization");
        if(autorizationHeader ==null){
            throw new RuntimeException("Token JWT não enviado no cabeçalho Autorization");
        }
        return autorizationHeader.replace("Bearer ","");
    }
}
