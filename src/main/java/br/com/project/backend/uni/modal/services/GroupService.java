package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestGroupDTO;
import br.com.project.backend.uni.modal.entities.Group;
import br.com.project.backend.uni.modal.repositories.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public ResponseEntity getAllGroup(){
        List<Group> groupList = repository.findAll();
        return ResponseEntity.ok(groupList);
    }

    @Transactional
    public ResponseEntity createGroup(RequestGroupDTO data){
        Group group = new Group();
        group.setCreatedOn(LocalDate.now());
        group.setMessages(data.messages());
        group.setParticipant(data.participant());
        group.setType(data.type());
        repository.save(group);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateGroup(RequestGroupDTO data){
        Optional<Group> groupOptional = repository.findById(data.id());
        if (groupOptional.isPresent()){
            Group group = groupOptional.get();
            group.setCreatedOn(LocalDate.now());
            group.setMessages(data.messages());
            group.setParticipant(data.participant());
            group.setType(data.type());
            repository.save(group);
            return ResponseEntity.ok(group);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity deleteGroup(@PathVariable Long id){
        Optional<Group> optionalGroup = repository.findById(id);
        if (optionalGroup.isPresent()){
            Group group = optionalGroup.get();
            repository.delete(group);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
