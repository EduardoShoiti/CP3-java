package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.enums.AppointmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
        Long doctorId,

        AppointmentStatus status,

        @Future
        LocalDateTime dateTime
) {
}
