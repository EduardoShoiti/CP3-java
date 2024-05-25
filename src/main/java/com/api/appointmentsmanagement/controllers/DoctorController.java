package com.api.appointmentsmanagement.controllers;

import com.api.appointmentsmanagement.controllers.dtos.*;
import com.api.appointmentsmanagement.services.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@RequestMapping("/api/doctor")
@SecurityRequirement(name = "basicAuth")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<DoctorListDTO>> create(@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO) {
        DoctorListDTO doctorListDTO = doctorService.create(doctorRegisterDTO);

        return ResponseEntity
                .created(linkTo(methodOn(DoctorController.class).findById(doctorListDTO.id())).toUri())
                .body(EntityModel.of(doctorListDTO,
                        linkTo(methodOn(DoctorController.class).findById(doctorListDTO.id())).withSelfRel(),
                        linkTo(methodOn(DoctorController.class).find(null)).withRel("doctors")));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> find(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DoctorListDTO> page = doctorService.find(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedDoctorDTO> findById(@PathVariable("id") Long id) {
        DetailedDoctorDTO detailedDoctorDTO = doctorService.findById(id);
        return ResponseEntity.ok(detailedDoctorDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DetailedDoctorDTO> update(@RequestBody DoctorUpdateDTO doctorUpdateDTO, @PathVariable("id") Long id) {
        DetailedDoctorDTO detailedDoctorDTO = doctorService.update(id, doctorUpdateDTO);
        return ResponseEntity.ok(detailedDoctorDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<DoctorListDTO>> findAllDoctorsHateoas(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        List<EntityModel<DoctorListDTO>> doctors = doctorService.find(pagination).stream()
                .map(doctor -> EntityModel.of(doctor,
                        linkTo(methodOn(DoctorController.class).findById(doctor.id())).withSelfRel()))
                .toList();

        return CollectionModel.of(doctors, linkTo(methodOn(DoctorController.class).find(null)).withSelfRel());
    }
}
