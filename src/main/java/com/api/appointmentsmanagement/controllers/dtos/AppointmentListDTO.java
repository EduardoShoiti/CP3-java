package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.enums.AppointmentStatus;
import com.api.appointmentsmanagement.models.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record AppointmentListDTO(
        @JsonIgnoreProperties({"appointmentList"})
        DetailedPatientDTO patient,

        Long id,

        AppointmentStatus status,

        LocalDateTime dateTime
) {
    public AppointmentListDTO(Appointment appointment) {
        this(new DetailedPatientDTO(appointment.getPatient()), appointment.getId(), appointment.getStatus(), appointment.getDateTime());
    }
}
