package com.api.appointmentsmanagement.models;

import com.api.appointmentsmanagement.controllers.dtos.ClinicRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.ClinicUpdateDTO;
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
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private List<Doctor> doctorsList;

    public Clinic(ClinicRegisterDTO clinicRegisterDTO) {
        this.address = clinicRegisterDTO.address();
        this.name = clinicRegisterDTO.name();
        this.phone = clinicRegisterDTO.phone();
    }

    public void updateInformation(ClinicUpdateDTO clinicUpdateDTO) {
        if (clinicUpdateDTO.address() != null) {
            this.address = clinicUpdateDTO.address();
        }

        if (clinicUpdateDTO.phone() != null) {
            this.phone = clinicUpdateDTO.phone();
        }

        if (clinicUpdateDTO.name() != null) {
            this.name = clinicUpdateDTO.name();
        }
    }
}
