package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestAgendaDTO;
import br.com.project.backend.uni.modal.entities.Agenda;
import br.com.project.backend.uni.modal.repositories.AgendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {


    @Autowired
    private AgendaRepository repository;

    public ResponseEntity getAllAgenda(){
        List<Agenda> agendaList = repository.findAll();
        return ResponseEntity.ok(agendaList);
    }

    @Transactional
    public ResponseEntity createAgenda(RequestAgendaDTO data){
        Agenda agenda = new Agenda();
        agenda.setDateEvent(LocalDate.now());
        agenda.setType(data.type());
        agenda.setEvent(data.event());
        agenda.setDescription(data.description());
        repository.save(agenda);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateAgenda(RequestAgendaDTO data){
        Optional<Agenda> agendaOptional = repository.findById(data.id());
        if (agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            agenda.setDateEvent(LocalDate.now());
            agenda.setType(data.type());
            agenda.setEvent(data.event());
            agenda.setDescription(data.description());
            repository.save(agenda);
            return ResponseEntity.ok(agenda);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity deleteAgenda(@PathVariable Long id){
        Optional<Agenda> optionalAgenda = repository.findById(id);
        if (optionalAgenda.isPresent()){
            Agenda agenda = optionalAgenda.get();
            repository.delete(agenda);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
