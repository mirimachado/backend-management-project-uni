package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.services.GalleryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping
    public ResponseEntity getAllGallery(){
        return galleryService.getAllGallery();
    }

    @PostMapping("/create")
    public ResponseEntity createGallery(
            @RequestPart("data") @Valid RequestGalleryDTO data,
            @RequestParam("file") MultipartFile file){
        return galleryService.createGallery(data, file);
    }

    @PutMapping("/update")
    public ResponseEntity updateGallery(@RequestPart("data") @Valid RequestGalleryDTO data,
                                         @RequestParam("file") MultipartFile file){
        return galleryService.updateGallery(data, file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGallery(@PathVariable Long id){
        return galleryService.deleteGallery(id);
    }

}
