package com.api.appointmentsmanagement.controllers.dtos;

import com.api.appointmentsmanagement.models.Clinic;
import com.api.appointmentsmanagement.models.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record DetailedClinicDTO(
        String name,
        String address,
        String phone,
        @JsonIgnoreProperties({"clinic"})
        List<DetailedDoctorDTO> doctorList
) {
    public DetailedClinicDTO(Clinic clinic) {
        this(clinic.getName(), clinic.getAddress(), clinic.getPhone(), clinic.getDoctorsList().stream().map(DetailedDoctorDTO::new).toList());
    }
}
