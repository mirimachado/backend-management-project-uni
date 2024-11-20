package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorInfoDTO;
import br.com.project.backend.uni.modal.entities.VisitorInfo;
import br.com.project.backend.uni.modal.repositories.VisitorInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorInfoServiceTest {

    @Mock
    private VisitorInfoRepository repository;

    @InjectMocks
    private VisitorInfoService visitorInfoService;

    @Test
    void getAllVisitorInfo() {
        List<VisitorInfo> visitorInfoObj = repository.findAll();
        when(repository.findAll()).thenReturn(visitorInfoObj);
        ResponseEntity result = visitorInfoService.getAllVisitorInfo();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createVisitorInfo() {
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(1L, "Event test", "midia social","Test type");
        VisitorInfo visitorInfo = new VisitorInfo(data);
        when(repository.save(visitorInfo)).thenReturn(visitorInfo);
        ResponseEntity result = this.visitorInfoService.createVisitorInfo(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void updateVisitorInfoCase1() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(1L, "Event test", "midia social","Test type");
        VisitorInfo visitorInfo = new VisitorInfo(data);
        when(repository.findById(id)).thenReturn(Optional.of(visitorInfo));
        when(repository.save(visitorInfo)).thenReturn(visitorInfo);
        ResponseEntity resultF = visitorInfoService.updateVisitorInfo(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void updateVisitorInfoCase2() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(1L, "Event test", "midia social","Test type");
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = visitorInfoService.updateVisitorInfo(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteVisitorInfoCase1() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(1L, "Event test", "midia social","Test type");
        VisitorInfo visitorInfo = new VisitorInfo(data);
        when(repository.findById(id)).thenReturn(Optional.of(visitorInfo));
        ResponseEntity resultF = visitorInfoService.deleteVisitorInfo(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    void deleteVisitorInfoCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = visitorInfoService.deleteVisitorInfo(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}