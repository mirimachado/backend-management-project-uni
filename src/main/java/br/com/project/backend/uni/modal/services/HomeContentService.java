package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import br.com.project.backend.uni.modal.entities.HomeContent;
import br.com.project.backend.uni.modal.repositories.HomeContentRepository;
import br.com.project.backend.uni.modal.services.utility.MongoDBServiceDelete;
import br.com.project.backend.uni.modal.services.utility.UploadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class HomeContentService {

    @Autowired
    private HomeContentRepository repository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MongoDBServiceDelete mongoDBServiceDelete;

    public ResponseEntity getHomeContent(){
        List<HomeContent> homeContents = repository.findAll();
        return ResponseEntity.ok(homeContents);
    }

    @Transactional
    public ResponseEntity updateHomeContent(RequestHomeContentDTO data, MultipartFile file){
        Optional<HomeContent> homeContents = repository.findById(data.id());
        if (homeContents.isPresent()){

            HomeContent homeContent = homeContents.get();
            mongoDBServiceDelete.deleteImageMongo(homeContent.getPhotoId());
            String updatedPhotoId = uploadService.savePhoto(file);
            homeContent.setTitle(data.title());
            homeContent.setContent(data.title());
            homeContent.setPhotoId(updatedPhotoId);
            repository.save(homeContent);
            return ResponseEntity.ok(homeContent);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity createHomeContent(RequestHomeContentDTO data, MultipartFile file){

        String generatedphotoId = uploadService.savePhoto(file);
        HomeContent homeContent = new HomeContent();
        homeContent.setTitle(data.title());
        homeContent.setContent(data.content());
        homeContent.setPhotoId(generatedphotoId);

        repository.save(homeContent);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteHomeContent(@PathVariable Long id){
        Optional<HomeContent> optionalHomeContent = repository.findById(id);
        if (optionalHomeContent.isPresent()){
            HomeContent homeContent = optionalHomeContent.get();
            repository.delete(homeContent);
            mongoDBServiceDelete.deleteImageMongo(homeContent.getPhotoId());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
      }

    }


