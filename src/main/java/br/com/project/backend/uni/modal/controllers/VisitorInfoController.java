package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorInfoDTO;
import br.com.project.backend.uni.modal.services.VisitorInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitor_info")
public class VisitorInfoController {

    @Autowired
    private VisitorInfoService visitorInfoService;


    @GetMapping
    public ResponseEntity getAllVisitorInfo() {
        return visitorInfoService.getAllVisitorInfo();
    }

    @PostMapping("/create")
    public ResponseEntity createVisitorInfo(@RequestBody @Valid RequestVisitorInfoDTO data){
        return visitorInfoService.createVisitorInfo(data);
    }

    @PutMapping("/update")
    public ResponseEntity updateVisitorInfo(@RequestBody @Valid RequestVisitorInfoDTO data) {
        return visitorInfoService.updateVisitorInfo(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVisitorInfo(@PathVariable Long id) {
        return visitorInfoService.deleteVisitorInfo(id);
    }

}
