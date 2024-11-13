package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.entities.Gallery;
import br.com.project.backend.uni.modal.repositories.GalleryRepository;
import br.com.project.backend.uni.modal.services.utility.MongoDBServiceDelete;
import br.com.project.backend.uni.modal.services.utility.UploadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository repository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MongoDBServiceDelete mongoDBServiceDelete;

    public ResponseEntity getAllGallery(){
        List<Gallery> galleryList = repository.findAll();
        return ResponseEntity.ok(galleryList);
    }

    @Transactional
    public ResponseEntity createGallery(RequestGalleryDTO data, MultipartFile file){

        String generatedphotoId = uploadService.savePhoto(file);
        Gallery gallery = new Gallery();
        gallery.setDatePhoto(LocalDate.now());
        gallery.setTitle(data.title());
        gallery.setType(data.type());
        gallery.setDescription(data.description());
        gallery.setPhotoId(generatedphotoId);

        repository.save(gallery);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateGallery(RequestGalleryDTO data, MultipartFile multipartFile){
        Optional<Gallery> galleryOptional = repository.findById(data.id());
        if (galleryOptional.isPresent()){

            Gallery gallery = galleryOptional.get();
            mongoDBServiceDelete.deleteImageMongo(gallery.getPhotoId());
            String updatedPhotoId = uploadService.savePhoto(multipartFile);
            gallery.setDatePhoto(LocalDate.now());
            gallery.setTitle(data.title());
            gallery.setPhotoId(updatedPhotoId);
            gallery.setType(data.type());
            gallery.setDescription(data.description());
            repository.save(gallery);

            return ResponseEntity.ok(gallery);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity deleteGallery(@PathVariable Long id){
        Optional<Gallery> optionalGallery = repository.findById(id);
        if (optionalGallery.isPresent()){
            Gallery gallery = optionalGallery.get();

            repository.delete(gallery);
            mongoDBServiceDelete.deleteImageMongo(gallery.getPhotoId());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
