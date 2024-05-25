package com.api.appointmentsmanagement.enums;

public enum AppointmentStatus {
    SCHEDULED("Agendada"),
    CANCELED("Cancelada"),
    FINISHED("Finalizada"),
    RESCHEDULED("Reagendada");

    private String status;

    AppointmentStatus(String status) {
        this.status = status;
    }

    private String getStatus() {
        return status;
    }
}
