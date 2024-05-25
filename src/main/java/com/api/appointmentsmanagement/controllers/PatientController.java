package com.api.appointmentsmanagement.controllers;

import com.api.appointmentsmanagement.controllers.dtos.*;
import com.api.appointmentsmanagement.services.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/patient")
@SecurityRequirement(name = "basicAuth")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<PatientListDTO>> create(@RequestBody @Valid PatientRegisterDTO patientRegisterDTO) {
        PatientListDTO patientListDTO = patientService.create(patientRegisterDTO);

        return ResponseEntity
                .created(linkTo(methodOn(PatientController.class).findById(patientListDTO.id())).toUri())
                .body(EntityModel.of(patientListDTO,
                        linkTo(methodOn(PatientController.class).findById(patientListDTO.id())).withSelfRel(),
                        linkTo(methodOn(PatientController.class).find(null)).withRel("patients")));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientListDTO>> find(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<PatientListDTO> page = patientService.find(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DetailedPatientDTO> findById(@PathVariable("id") Long id) {
        DetailedPatientDTO detailedPatientDTO = patientService.findById(id);
        return ResponseEntity.ok(detailedPatientDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DetailedPatientDTO> update(@RequestBody PatientUpdateDTO patientUpdateDTO, @PathVariable("id") Long id) {
        DetailedPatientDTO detailedPatientDTO = patientService.update(id, patientUpdateDTO);
        return ResponseEntity.ok(detailedPatientDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas")
    @PreAuthorize("hasRole('ADMIN')")
    public CollectionModel<EntityModel<PatientListDTO>> findAllPatientsHateoas(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        List<EntityModel<PatientListDTO>> patients = patientService.find(pagination).stream()
                .map(patient -> EntityModel.of(patient,
                        linkTo(methodOn(PatientController.class).findById(patient.id())).withSelfRel()))
                .toList();

        return CollectionModel.of(patients, linkTo(methodOn(PatientController.class).find(null)).withSelfRel());
    }
}
