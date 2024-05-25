package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.enums.AppointmentStatus;
import com.api.appointmentsmanagement.models.Appointment;
import com.api.appointmentsmanagement.models.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record DetailedAppointmentDTO(
        @JsonIgnoreProperties({"appointmentList", "id"})
        Patient patient,
        DetailedDoctorDTO doctor,
        AppointmentStatus status,
        LocalDateTime dateTime
) {
    public DetailedAppointmentDTO(Appointment appointment) {
        this(appointment.getPatient(), new DetailedDoctorDTO(appointment.getDoctor()), appointment.getStatus(), appointment.getDateTime());
    }
}
