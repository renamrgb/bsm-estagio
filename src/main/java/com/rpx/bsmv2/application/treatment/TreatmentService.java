package com.rpx.bsmv2.application.treatment;

import com.rpx.bsmv2.application.exceptions.business.BusinessDatabaseViolationException;
import com.rpx.bsmv2.application.exceptions.business.BusinessResourceNotFoundException;
import com.rpx.bsmv2.domain.treatment.Treatment;
import com.rpx.bsmv2.domain.treatment.TreatmentRepository;
import com.rpx.bsmv2.domain.treatment.TreatmentResource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Transactional(readOnly = true)
    public List<TreatmentResource> findALl() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments
                .stream()
                .map(treatment -> TreatmentResource.builder().build().convertTo(treatment))
                .toList();
    }

    @Transactional(readOnly = true)
    public TreatmentResource findById(final Long id) {
        return TreatmentResource.builder().build().convertTo(this.findByIdOrThrow(id));
    }

    @Transactional
    public TreatmentResource create(TreatmentResource resource) {
        resource.setId(null);
        Treatment treatment = resource.convertFrom();
        return saveOrUpdate(treatment);
    }

    @Transactional
    public TreatmentResource update(final Long id, TreatmentResource resource) {
        resource.setId(id);
        Treatment treatment = this.findByIdOrThrow(id);
        treatment = resource.convertToUpdate(treatment);
        return saveOrUpdate(treatment);
    }

    public void delete(final Long id) {
        try {
            treatmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessResourceNotFoundException("NOT FOUND");
        } catch (DataIntegrityViolationException e) {
            throw new BusinessDatabaseViolationException("VIOLÇÃO DE INTEGRIDADE NO BD");
        }
    }

    private TreatmentResource saveOrUpdate(Treatment treatment) {
        treatment = treatmentRepository.save(treatment);
        return TreatmentResource.builder().build().convertTo(treatment);
    }

    private Treatment findByIdOrThrow(final Long id) {
        Optional<Treatment> treatmentOpt = treatmentRepository.findById(id);
        return treatmentOpt.orElseThrow(() -> new BusinessResourceNotFoundException("NOT FOUND"));
    }

}
