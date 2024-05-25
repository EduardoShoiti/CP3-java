package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

public record DetailedPatientDTO(
        String name,
        String address,
        String gender,
        LocalDate dateOfBirth,
        String medicalHistory,
        String phone,

        @JsonIgnoreProperties({"patient"})
        List<DetailedAppointmentDTO> appointmentList
) {
    public DetailedPatientDTO(Patient patient) {
        this(patient.getName(), patient.getAddress(), patient.getGender(), patient.getDateOfBirth(), patient.getMedicalHistory(), patient.getPhone(), patient.getAppointmentList().stream().map(DetailedAppointmentDTO::new).toList());
    }
}
