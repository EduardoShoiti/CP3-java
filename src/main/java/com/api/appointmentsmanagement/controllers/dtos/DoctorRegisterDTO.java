package com.api.appointmentsmanagement.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorRegisterDTO(
        @NotBlank
        String name,

        @NotBlank
        String specialty,

        @NotBlank
        String registerNumber,

        @NotNull
        Long clinicId
) {
}
