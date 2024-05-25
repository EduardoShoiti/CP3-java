package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Patient;

import java.time.LocalDate;

public record PatientListDTO(
        Long id,
        String name,

        String gender,

        LocalDate dateOfBirth
) {
    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getGender(), patient.getDateOfBirth());
    }
}
