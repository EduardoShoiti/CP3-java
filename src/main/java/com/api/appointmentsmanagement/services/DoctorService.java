package com.api.appointmentsmanagement.services;

import com.api.appointmentsmanagement.controllers.dtos.DetailedDoctorDTO;
import com.api.appointmentsmanagement.controllers.dtos.DoctorListDTO;
import com.api.appointmentsmanagement.controllers.dtos.DoctorRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.DoctorUpdateDTO;
import com.api.appointmentsmanagement.models.Clinic;
import com.api.appointmentsmanagement.models.Doctor;
import com.api.appointmentsmanagement.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    private final ClinicService clinicService;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ClinicService clinicService) {
        this.doctorRepository = doctorRepository;
        this.clinicService = clinicService;
    }

    public DoctorListDTO create(DoctorRegisterDTO doctorRegisterDTO) {
        Doctor doctor = new Doctor(doctorRegisterDTO);

        Clinic clinic = clinicService.get(doctorRegisterDTO.clinicId());

        doctor.setClinic(clinic);

        return new DoctorListDTO(doctorRepository.save(doctor));
    }

    public Page<DoctorListDTO> find(Pageable pagination) {
        return doctorRepository.findAll(pagination).map(DoctorListDTO::new);
    }

    public DetailedDoctorDTO findById(Long id) {
        return new DetailedDoctorDTO(doctorRepository.getReferenceById(id));
    }

    public DetailedDoctorDTO update(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.getReferenceById(id);

        doctor.updateInformation(doctorUpdateDTO);

        doctorRepository.save(doctor);

        return new DetailedDoctorDTO(doctor);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor get(Long id) {
        return doctorRepository.getReferenceById(id);
    }
}
