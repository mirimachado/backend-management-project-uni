package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorInfoDTO;
import br.com.project.backend.uni.modal.entities.VisitorInfo;
import br.com.project.backend.uni.modal.repositories.VisitorInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorInfoService {

    @Autowired
    private VisitorInfoRepository repository;

    public ResponseEntity getAllVisitorInfo(){
        List<VisitorInfo> visitorInfoList = repository.findAll();
        return ResponseEntity.ok(visitorInfoList);
    }

    @Transactional
    public ResponseEntity createVisitorInfo(RequestVisitorInfoDTO data){
        VisitorInfo visitorInfo = new VisitorInfo();
        visitorInfo.setAboutUs(data.aboutUs());
        visitorInfo.setLeadership(data.leadership());
        visitorInfo.setSocialMedia(data.socialMedia());
        repository.save(visitorInfo);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateVisitorInfo(RequestVisitorInfoDTO data){
        Optional<VisitorInfo> visitorInfoOptional = repository.findById(data.id());
        if (visitorInfoOptional.isPresent()){
            VisitorInfo visitorInfo = visitorInfoOptional.get();
            visitorInfo.setAboutUs(data.aboutUs());
            visitorInfo.setLeadership(data.leadership());
            visitorInfo.setSocialMedia(data.socialMedia());
            repository.save(visitorInfo);
            return ResponseEntity.ok(visitorInfo);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity deleteVisitorInfo(@PathVariable Long id){
        Optional<VisitorInfo> optionalVisitorInfo = repository.findById(id);
        if (optionalVisitorInfo.isPresent()){
            VisitorInfo visitorInfo = optionalVisitorInfo.get();
            repository.delete(visitorInfo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
