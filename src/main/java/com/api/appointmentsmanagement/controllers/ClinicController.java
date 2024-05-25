package com.api.appointmentsmanagement.controllers;

import com.api.appointmentsmanagement.controllers.dtos.*;
import com.api.appointmentsmanagement.services.ClinicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
@RequestMapping("/api/clinic")
@SecurityRequirement(name = "basicAuth")
public class ClinicController {
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<ClinicListDTO>> create(@RequestBody @Valid ClinicRegisterDTO clinicRegisterDTO) {
        ClinicListDTO clinicListDTO = clinicService.create(clinicRegisterDTO);

        return ResponseEntity
                .created(linkTo(methodOn(ClinicController.class).findById(clinicListDTO.id())).toUri())
                .body(EntityModel.of(clinicListDTO,
                        linkTo(methodOn(ClinicController.class).findById(clinicListDTO.id())).withSelfRel(),
                        linkTo(methodOn(ClinicController.class).find(null)).withRel("clinics")));
    }

    @GetMapping
    public ResponseEntity<Page<ClinicListDTO>> find(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<ClinicListDTO> page = clinicService.find(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedClinicDTO> findById(@PathVariable("id") Long id) {
        DetailedClinicDTO detailedClinicDTO = clinicService.findById(id);
        return ResponseEntity.ok(detailedClinicDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DetailedClinicDTO> update(@RequestBody ClinicUpdateDTO clinicUpdateDTO, @PathVariable("id") Long id) {
        DetailedClinicDTO detailedClinicDTO = clinicService.update(id, clinicUpdateDTO);
        return ResponseEntity.ok(detailedClinicDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<ClinicListDTO>> findAllClinicsHateoas(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        List<EntityModel<ClinicListDTO>> clinics = clinicService.find(pagination).stream()
                .map(clinic -> EntityModel.of(clinic,
                        linkTo(methodOn(ClinicController.class).findById(clinic.id())).withSelfRel()))
                .toList();

        return CollectionModel.of(clinics, linkTo(methodOn(ClinicController.class).find(null)).withSelfRel());
    }
}
