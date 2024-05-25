package com.api.appointmentsmanagement.models;

import com.api.appointmentsmanagement.controllers.dtos.PatientRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.PatientUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "medical_history")
    private String medicalHistory;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private List<Appointment> appointmentList;

    public Patient(PatientRegisterDTO patientRegisterDTO) {
        this.address = patientRegisterDTO.address();
        this.gender = patientRegisterDTO.address();
        this.medicalHistory = patientRegisterDTO.medicalHistory();
        this.dateOfBirth = patientRegisterDTO.dateOfBirth();
        this.name = patientRegisterDTO.name();
        this.phone = patientRegisterDTO.phone();
    }

    public void updateInformation(PatientUpdateDTO patientUpdateDTO) {
        if (patientUpdateDTO.address() != null) {
            this.address = patientUpdateDTO.address();
        }

        if (patientUpdateDTO.gender() != null) {
            this.gender = patientUpdateDTO.gender();
        }

        if (patientUpdateDTO.medicalHistory() != null) {
            this.medicalHistory = patientUpdateDTO.medicalHistory();
        }

        if (patientUpdateDTO.dateOfBirth() != null) {
            this.dateOfBirth = patientUpdateDTO.dateOfBirth();
        }

        if (patientUpdateDTO.name() != null) {
            this.name = patientUpdateDTO.name();
        }

        if (patientUpdateDTO.phone() != null) {
            this.phone = patientUpdateDTO.phone();
        }
    }
}
