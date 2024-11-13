package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorDTO;
import br.com.project.backend.uni.modal.entities.Visitor;
import br.com.project.backend.uni.modal.repositories.VisitorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository repository;

    public ResponseEntity getAllVisitors(){
        List<Visitor> optionalVisitor = repository.findAll();
        return ResponseEntity.ok(optionalVisitor);
    }

    @Transactional
    public ResponseEntity createVisitor(RequestVisitorDTO data){
            Visitor visitor = new Visitor();
            visitor.setVisitorName(data.visitorName());
            visitor.setDateVisit(LocalDate.now());
            repository.save(visitor);
            return ResponseEntity.ok().build();
    }

}
