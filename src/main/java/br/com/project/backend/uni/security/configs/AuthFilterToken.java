package br.com.project.backend.uni.security.configs;

import br.com.project.backend.uni.security.jwt.JwtUtils;
import br.com.project.backend.uni.security.services.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilterToken extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getToken(request);
            System.out.println("Token extraído: " + jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)){

                String username = jwtUtils.getUsernameToken(jwt);
                System.out.println("Username extraído do token: " + username);
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                System.out.println("Authorities do UserDetails: " + userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Autenticação configurada no SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication());
            }
            else {
                System.out.println("Token inválido ou inexistente.");
            }
        }catch (Exception e){
            System.out.println("Ocorreu um erro ao processar Token. ");

        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String headerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")){
            return headerToken.replace("Bearer ","");
        }
        return null;
    }

}
