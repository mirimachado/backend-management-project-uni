package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestAgendaDTO;
import br.com.project.backend.uni.modal.services.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity getAllAgenda(){
        return agendaService.getAllAgenda();
    }

    @PostMapping("/create")
    public ResponseEntity createAgenda(@RequestBody @Valid RequestAgendaDTO data){
        return agendaService.createAgenda(data);
    }

    @PutMapping("/update")
    public ResponseEntity updateAgenda(@RequestBody @Valid RequestAgendaDTO data){
        return agendaService.updateAgenda(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAgenda(@PathVariable Long id){
        return agendaService.deleteAgenda(id);
    }

}
