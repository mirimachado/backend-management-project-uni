package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.dtos.requests.RequestUserPassword;
import br.com.project.backend.uni.modal.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Valid RequestUserDTO data){
        return userService.createUser(data);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid RequestUserDTO data) {
        return userService.updateUser(data);
    }

    @PostMapping("/update_password")
    public ResponseEntity updatePassword(@RequestBody @Valid RequestUserPassword data) {
        return userService.updatePassword(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
