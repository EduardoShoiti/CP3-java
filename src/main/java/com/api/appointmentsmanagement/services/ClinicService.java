package com.api.appointmentsmanagement.services;

import com.api.appointmentsmanagement.controllers.dtos.ClinicListDTO;
import com.api.appointmentsmanagement.controllers.dtos.ClinicRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.ClinicUpdateDTO;
import com.api.appointmentsmanagement.controllers.dtos.DetailedClinicDTO;
import com.api.appointmentsmanagement.models.Clinic;
import com.api.appointmentsmanagement.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public ClinicListDTO create(ClinicRegisterDTO clinicRegisterDTO) {
        Clinic clinic = new Clinic(clinicRegisterDTO);

        return new ClinicListDTO(clinicRepository.save(clinic));
    }

    public Page<ClinicListDTO> find(Pageable pagination) {
        return clinicRepository.findAll(pagination).map(ClinicListDTO::new);
    }

    public DetailedClinicDTO findById(Long id) {
        return new DetailedClinicDTO(clinicRepository.getReferenceById(id));
    }

    public DetailedClinicDTO update(Long id, ClinicUpdateDTO clinicUpdateDTO) {
        Clinic clinic = clinicRepository.getReferenceById(id);

        clinic.updateInformation(clinicUpdateDTO);

        clinicRepository.save(clinic);

        return new DetailedClinicDTO(clinic);
    }

    public void delete(Long id) {
        clinicRepository.deleteById(id);
    }

    public Clinic get(Long id) {
        return clinicRepository.getReferenceById(id);
    }
}
