package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import br.com.project.backend.uni.modal.services.HomeContentService;
import com.mongodb.lang.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home_content")
public class HomeContentController {

    @Autowired
    private HomeContentService homeContentService;

    @GetMapping
    public ResponseEntity getAllUsers(){
        return homeContentService.getHomeContent();
    }

    @PutMapping("/update")
    public ResponseEntity updateHomeContent(@RequestPart("data") @Valid RequestHomeContentDTO data,
                                            @RequestParam("file") MultipartFile file){
        return homeContentService.updateHomeContent(data, file);
    }
    @PostMapping("/create")
    public ResponseEntity createHomeContent(@RequestPart("data") @Valid RequestHomeContentDTO data,
                                           @RequestParam("file") MultipartFile file){
        return homeContentService.createHomeContent(data, file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHomeContent(@PathVariable Long id){
        return homeContentService.deleteHomeContent(id);
    }

}
