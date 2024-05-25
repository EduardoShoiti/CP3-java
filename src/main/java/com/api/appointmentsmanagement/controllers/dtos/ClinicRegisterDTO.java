package com.api.appointmentsmanagement.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClinicRegisterDTO(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
        String phone,

        @NotBlank
        String address
) {
}
