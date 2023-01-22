package com.rpx.bsmv2.api.treatment;

import com.rpx.bsmv2.application.treatment.TreatmentService;
import com.rpx.bsmv2.domain.treatment.TreatmentResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
class TreatmentControllerTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "DESCRIÇÃO";
    @InjectMocks
    private TreatmentController controller;

    @Mock
    private TreatmentService service;


    @DisplayName("DEVE RETORNAR UMA LISTA COM 2 TreatmentResource")
    @Test
    void should_return_list_treatmentResource() {
        TreatmentResource treatmentResource = treatmentResourceFactory();
        Mockito.when(service.findALl()).thenReturn(List.of(treatmentResource, treatmentResource));

        List<TreatmentResource> response = controller.findAll();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(DESCRIPTION, response.get(0).getDescription());
        Assertions.assertEquals(ID, response.get(0).getId());
        Assertions.assertTrue(response.get(0).getIsActive());

        Mockito.verify(service).findALl();
    }

    @DisplayName("DEVE RETORNAR UM TreatmentResource")
    @Test
    void should_return_treatmentResource() {
        TreatmentResource treatmentResource = treatmentResourceFactory();
        Mockito.when(service.findById(ID)).thenReturn(treatmentResource);

        TreatmentResource response = controller.findById(ID);

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRIPTION, response.getDescription());
        Assertions.assertTrue(response.getIsActive());

        Mockito.verify(service).findById(ID);
    }

    @DisplayName("DEVE CADASTRAR UM Treatment E RETORNAR")
    @Test
    void should_persist() {
        TreatmentResource treatmentResource = treatmentResourceFactory();
        Mockito.when(service.create(treatmentResource)).thenReturn(treatmentResource);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<TreatmentResource> response = controller.insert(treatmentResource);

        Assertions.assertEquals(201, response.getStatusCode().value());
        Assertions.assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        Assertions.assertEquals(DESCRIPTION, response.getBody().getDescription());
        Assertions.assertTrue(response.getBody().getIsActive());

        Mockito.verify(service).create(treatmentResource);
    }

    @DisplayName("DEVE ATUALIZAR UM Treatment")
    @Test
    void should_update() {
        TreatmentResource treatmentResource = treatmentResourceFactory();
        TreatmentResource treatmentResourceAtualizado = treatmentResourceFactory();
        treatmentResourceAtualizado.setDescription("ATUALIZADO");

        Mockito.when(service.update(ID, treatmentResource)).thenReturn(treatmentResourceAtualizado);

        TreatmentResource response = controller.update(ID, treatmentResource);

        Assertions.assertEquals("ATUALIZADO", response.getDescription());

        Mockito.verify(service).update(ID, treatmentResource);
    }

    @DisplayName("DEVE DELETAR UM Teatment")
    @Test
    void should_delete() {
        controller.delete(ID);
        Mockito.verify(service).delete(ID);
    }

    private TreatmentResource treatmentResourceFactory() {
        return TreatmentResource.builder()
                .withDescription(DESCRIPTION)
                .withIsActive(true)
                .withId(ID)
                .build();
    }
}