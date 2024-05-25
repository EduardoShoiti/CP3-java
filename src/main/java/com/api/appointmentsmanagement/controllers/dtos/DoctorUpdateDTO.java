package com.api.appointmentsmanagement.controllers.dtos;

public record DoctorUpdateDTO(
        String name,

        String specialty,

        String registerNumber
) {
}
