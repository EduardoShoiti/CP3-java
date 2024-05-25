package com.api.appointmentsmanagement.models;

import com.api.appointmentsmanagement.controllers.dtos.AppointmentRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.AppointmentUpdateDTO;
import com.api.appointmentsmanagement.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment(AppointmentRegisterDTO appointmentRegisterDTO) {
        this.dateTime = appointmentRegisterDTO.dateTime();
        this.status = appointmentRegisterDTO.status();
    }

    public void updateInformation(AppointmentUpdateDTO appointmentUpdateDTO) {
        if (appointmentUpdateDTO.dateTime() != null) {
            this.dateTime = appointmentUpdateDTO.dateTime();
        }

        if (appointmentUpdateDTO.status() != null) {
            this.status = appointmentUpdateDTO.status();
        }
    }
}
