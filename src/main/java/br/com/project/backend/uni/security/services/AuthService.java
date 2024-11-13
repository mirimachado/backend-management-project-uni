package br.com.project.backend.uni.security.services;

import br.com.project.backend.uni.modal.entities.User;
import br.com.project.backend.uni.modal.repositories.UserRepository;
import br.com.project.backend.uni.security.dtos.AccessDTO;
import br.com.project.backend.uni.security.dtos.AuthenticationDTO;
import br.com.project.backend.uni.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository repository;

    public AccessDTO login(AuthenticationDTO authDTO){

        try {
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(userAuth);
        UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();

        String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
        Optional<User> optionalUser = repository.findByUsername(userAuthenticate.getUsername());
        String userRole = String.valueOf(optionalUser.get().getRole());

        AccessDTO accessDTO = new AccessDTO(token, userAuthenticate.getUsername(), userRole);
        return accessDTO;

        }catch (BadCredentialsException e ){
            System.out.println(e.getMessage());
        }
        return new AccessDTO("Acesso negado.");
    }
}
