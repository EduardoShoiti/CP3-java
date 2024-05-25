package com.api.appointmentsmanagement.controllers;

import com.api.appointmentsmanagement.controllers.dtos.*;
import com.api.appointmentsmanagement.services.AppointmentService;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@SecurityRequirement(name = "basicAuth")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<AppointmentListDTO>> create(@RequestBody @Valid AppointmentRegisterDTO appointmentRegisterDTO) {
        AppointmentListDTO appointmentListDTO = appointmentService.create(appointmentRegisterDTO);

        return ResponseEntity
                .created(linkTo(methodOn(AppointmentController.class).findById(appointmentListDTO.id())).toUri())
                .body(EntityModel.of(appointmentListDTO,
                        linkTo(methodOn(AppointmentController.class).findById(appointmentListDTO.id())).withSelfRel(),
                        linkTo(methodOn(AppointmentController.class).find(null)).withRel("appointments")));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<AppointmentListDTO>> find(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<AppointmentListDTO> page = appointmentService.find(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DetailedAppointmentDTO> findById(@PathVariable("id") Long id) {
        DetailedAppointmentDTO detailedAppointmentDTO = appointmentService.findById(id);
        return ResponseEntity.ok(detailedAppointmentDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DetailedAppointmentDTO> update(@RequestBody AppointmentUpdateDTO appointmentUpdateDTO, @PathVariable("id") Long id) {
        DetailedAppointmentDTO detailedAppointmentDTO = appointmentService.update(id, appointmentUpdateDTO);
        return ResponseEntity.ok(detailedAppointmentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER)")
    public CollectionModel<EntityModel<AppointmentListDTO>> findAllAppointmentsHateoas(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        List<EntityModel<AppointmentListDTO>> appointments = appointmentService.find(pagination).stream()
                .map(appointment -> EntityModel.of(appointment,
                        linkTo(methodOn(AppointmentController.class).findById(appointment.id())).withSelfRel()))
                .toList();

        return CollectionModel.of(appointments, linkTo(methodOn(AppointmentController.class).find(null)).withSelfRel());
    }
}
