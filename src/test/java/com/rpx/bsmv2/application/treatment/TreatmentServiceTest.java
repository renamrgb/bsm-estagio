package com.rpx.bsmv2.application.treatment;


import com.rpx.bsmv2.application.exceptions.business.BusinessResourceNotFoundException;
import com.rpx.bsmv2.domain.treatment.Treatment;
import com.rpx.bsmv2.domain.treatment.TreatmentRepository;
import com.rpx.bsmv2.domain.treatment.TreatmentResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class TreatmentServiceTest {

    @InjectMocks
    private TreatmentService service;

    @Mock
    private TreatmentRepository repository;

    private static final Long ID = 1L;
    private static final String DESCRICAO = "DESCRIÇÃO";

    @Test
    @DisplayName("DEVE RETORNAR UMA LISTA COM DOIS TreatmentResource")
    void should_returne_list() {
        Treatment treatment = treametFactory();
        Mockito.when(repository.findAll()).thenReturn(List.of(treatment, treatment));

        List<TreatmentResource> response = service.findALl();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(ID, response.get(0).getId());
        Assertions.assertEquals(DESCRICAO, response.get(0).getDescription());
        Assertions.assertTrue(response.get(0).getIsActive());

        Mockito.verify(repository).findAll();
    }

    @Test
    @DisplayName("DEVE RETORNAR UM TreatmentResource")
    void should_return_TreatmentResource_byId() {
        Treatment treatment = treametFactory();
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(treatment));

        TreatmentResource response = service.findById(ID);

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescription());
        Assertions.assertTrue(response.getIsActive());

        Mockito.verify(repository).findById(ID);
    }

    @DisplayName("DEVE RETORNAR UMA BusinessResourceNotFoundException CASO NÃO EXISTA POR ID")
    @Test
    void should_throw_when_notfound_byid() {
        Mockito.when(repository.findById(ID)).thenReturn(Optional.empty());

        BusinessResourceNotFoundException ex = Assertions.assertThrows(BusinessResourceNotFoundException.class, () -> {
            service.findById(ID);
        });

        Assertions.assertEquals("NOT FOUND", ex.getMessage());
        Mockito.verify(repository).findById(ID);
    }

    @DisplayName("DEVE CADASTRAR PERSISTIR UMA Treatment e retornar uma TreatmentResource")
    @Test
    void should_persist_and_return() {
        Treatment treatmentPrePersist = treametFactory();
        treatmentPrePersist.setId(null);
        Treatment treatmentPosPersist = treametFactory();
        TreatmentResource treatmentResource = treametResourceFactory();

        Mockito.when(repository.save(treatmentPrePersist)).thenReturn(treatmentPosPersist);

        TreatmentResource response = service.create(treatmentResource);

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescription());
        Assertions.assertTrue(response.getIsActive());

        Mockito.verify(repository).save(treatmentPrePersist);

    }

    @DisplayName("DEVE ATUALIZAR UMA Treatment e retornar uma TreatmentResource")
    @Test
    void should_update_and_return() {
        TreatmentResource treatmentResource = TreatmentResource
                .builder()
                .withId(ID)
                .withDescription("DESCRICAO ATUALIZADA")
                .withIsActive(false).build();

        Treatment treatment = treametFactory();

        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(treatment));
        Mockito.when(repository.save(treatment)).thenReturn(treatment);

        service.update(ID, treatmentResource);

        Assertions.assertEquals("DESCRICAO ATUALIZADA", treatment.getDescription());
        Assertions.assertEquals(1L, treatment.getId());
        Assertions.assertFalse(treatment.getIsActive());

        Mockito.verify(repository).save(treatment);
    }

    @DisplayName("DEVE RETORNAR UMA BusinessResourceNotFoundException QUANDO TENTAR ATUALIZAR UM OBJETO QUE NÃO EXISTE")
    @Test
    void should_throw_update_when_not_exist_byid() {
        TreatmentResource treatmentResourceMock = Mockito.mock(TreatmentResource.class);

        Mockito.when(repository.findById(ID)).thenReturn(Optional.empty());


        BusinessResourceNotFoundException ex = Assertions.assertThrows(BusinessResourceNotFoundException.class, () -> {
            service.update(ID, treatmentResourceMock);
        });

        Assertions.assertEquals("NOT FOUND", ex.getMessage());

        Mockito.verify(repository).findById(ID);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @DisplayName("DEVE DELETAR UM REGISTRO PELO ID")
    @Test
    void should_delete() {
        service.delete(ID);
        Mockito.verify(repository).deleteById(ID);
    }

    private TreatmentResource treametResourceFactory() {
        return TreatmentResource.builder()
                .withId(1L)
                .withIsActive(true)
                .withDescription(DESCRICAO)
                .build();
    }

    private Treatment treametFactory() {
        Treatment treatment = new Treatment();
        treatment.setId(ID);
        treatment.setIsActive(true);
        treatment.setDescription(DESCRICAO);
        return treatment;
    }
}