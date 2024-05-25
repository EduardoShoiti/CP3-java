package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Doctor;

public record DoctorListDTO(
        Long id,
        String name,
        String specialty
) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpecialty());
    }
}
