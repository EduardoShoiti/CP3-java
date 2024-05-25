package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Clinic;
import com.api.appointmentsmanagement.models.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record DetailedDoctorDTO(
        String name,
        String specialty,
        String registerNumber,
        @JsonIgnoreProperties({"doctorsList", "id"})
        Clinic clinic
) {
    public DetailedDoctorDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getSpecialty(), doctor.getRegisterNumber(), doctor.getClinic());
    }
}
