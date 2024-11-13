package br.com.project.backend.uni.security.controllers;

import br.com.project.backend.uni.security.dtos.AuthenticationDTO;
import br.com.project.backend.uni.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authDTO){
         return ResponseEntity.ok(authService.login(authDTO));
    }

}
