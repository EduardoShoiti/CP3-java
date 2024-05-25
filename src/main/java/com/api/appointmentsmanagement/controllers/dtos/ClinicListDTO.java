package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Clinic;

public record ClinicListDTO(
        Long id,
        String name
) {
    public ClinicListDTO(Clinic clinic) {
        this(clinic.getId(), clinic.getName());
    }
}
