package com.rpx.bsmv2.api.treatment;

import com.rpx.bsmv2.application.treatment.TreatmentService;
import com.rpx.bsmv2.domain.treatment.TreatmentResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "treatment")
public class TreatmentController {
    private final TreatmentService treatmentService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentResource> findAll() {
        return treatmentService.findALl();
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TreatmentResource findById(@PathVariable Long id) {
        return treatmentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> insert(@RequestBody @Valid TreatmentResource resource) {
        TreatmentResource response = treatmentService.create(resource);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TreatmentResource update (@PathVariable Long id, @RequestBody TreatmentResource resource) {
        return treatmentService.update(id, resource);
    }

    @DeleteMapping(value = "{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        treatmentService.delete(id);
    }
}
