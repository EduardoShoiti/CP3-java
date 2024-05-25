package com.api.appointmentsmanagement.controllers.dtos;

import java.time.LocalDate;
public record PatientUpdateDTO(
        String name,

        String address,

        String gender,

        LocalDate dateOfBirth,

        String medicalHistory,

        String phone
) {
}
