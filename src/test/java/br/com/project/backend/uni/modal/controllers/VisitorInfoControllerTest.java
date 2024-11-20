package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorInfoDTO;
import br.com.project.backend.uni.modal.services.VisitorInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorInfoControllerTest {

    @Mock
    private VisitorInfoService visitorInfoService;

    @InjectMocks
    private VisitorInfoController visitorInfoController;


    @Test
    void getAllVisitorInfo() {
        when(visitorInfoService.getAllVisitorInfo()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorInfoController.getAllVisitorInfo();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createVisitorInfo() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(id, "Event test", "midia social","Test type");
        when(visitorInfoService.createVisitorInfo(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorInfoController.createVisitorInfo(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void updateVisitorInfo() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(id, "Event test", "midia social","Test type");
        when(visitorInfoService.updateVisitorInfo(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorInfoController.updateVisitorInfo(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteVisitorInfo() {
        Long id = 1L;
        when(visitorInfoService.deleteVisitorInfo(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorInfoController.deleteVisitorInfo(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}