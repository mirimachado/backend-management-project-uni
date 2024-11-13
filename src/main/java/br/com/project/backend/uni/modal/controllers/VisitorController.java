package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorDTO;
import br.com.project.backend.uni.modal.services.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public ResponseEntity getAllVisitors() {
        return visitorService.getAllVisitors();
    }


    @PutMapping("/create")
    public ResponseEntity createVisitor(@RequestBody @Valid RequestVisitorDTO data){
        return visitorService.createVisitor(data);
    }

}
