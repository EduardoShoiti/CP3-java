package com.api.appointmentsmanagement.controllers.dtos;

public record ClinicUpdateDTO(
        String name,
        String address,
        String phone
) {
}
