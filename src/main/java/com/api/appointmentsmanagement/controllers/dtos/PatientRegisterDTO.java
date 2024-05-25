package com.api.appointmentsmanagement.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PatientRegisterDTO(
        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotBlank
        String gender,

        @Past
        @NotNull
        LocalDate dateOfBirth,

        @NotBlank
        String medicalHistory,

        @NotBlank
        @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
        String phone
) {
}
