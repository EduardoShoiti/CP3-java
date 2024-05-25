package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.enums.AppointmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRegisterDTO(
        @NotNull
        Long doctorId,

        @NotNull
        Long patientId,

        @Future
        @NotNull
        LocalDateTime dateTime,

        @NotNull
        AppointmentStatus status
) {
}
