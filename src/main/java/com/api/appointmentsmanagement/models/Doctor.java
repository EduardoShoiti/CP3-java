package com.api.appointmentsmanagement.models;

import com.api.appointmentsmanagement.controllers.dtos.DoctorRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.DoctorUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String specialty;

    private String name;

    @Column(name = "register_number")
    private String registerNumber;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointmentList;

    public Doctor(DoctorRegisterDTO doctorRegisterDTO) {
        this.registerNumber = doctorRegisterDTO.registerNumber();
        this.name = doctorRegisterDTO.name();
        this.specialty = doctorRegisterDTO.specialty();
    }

    public void updateInformation(DoctorUpdateDTO doctorUpdateDTO) {
        if (doctorUpdateDTO.name() != null) {
            this.name = doctorUpdateDTO.name();
        }

        if (doctorUpdateDTO.registerNumber() != null) {
            this.registerNumber = doctorUpdateDTO.registerNumber();
        }

        if (doctorUpdateDTO.specialty() != null) {
            this.specialty = doctorUpdateDTO.specialty();
        }
    }
}
