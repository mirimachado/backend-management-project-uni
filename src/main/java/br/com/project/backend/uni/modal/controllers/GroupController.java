package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestGroupDTO;
import br.com.project.backend.uni.modal.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity getAllGroup(){
        return groupService.getAllGroup();
    }
    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody @Valid RequestGroupDTO data){
        return groupService.createGroup(data);
    }
    @PutMapping("/update")
    public ResponseEntity updateGroup(@RequestBody @Valid RequestGroupDTO data){
        return groupService.updateGroup(data);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable Long id){
        return groupService.deleteGroup(id);
    }
}
