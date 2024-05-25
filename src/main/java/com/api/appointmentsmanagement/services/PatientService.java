package com.api.appointmentsmanagement.services;

import com.api.appointmentsmanagement.controllers.dtos.DetailedPatientDTO;
import com.api.appointmentsmanagement.controllers.dtos.PatientListDTO;
import com.api.appointmentsmanagement.controllers.dtos.PatientRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.PatientUpdateDTO;
import com.api.appointmentsmanagement.models.Patient;
import com.api.appointmentsmanagement.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientListDTO create(PatientRegisterDTO patientRegisterDTO) {
        Patient patient = new Patient(patientRegisterDTO);

        return new PatientListDTO(patientRepository.save(patient));
    }

    public Page<PatientListDTO> find(Pageable pagination) {
        return patientRepository.findAll(pagination).map(PatientListDTO::new);
    }

    public DetailedPatientDTO findById(Long id) {
        return new DetailedPatientDTO(patientRepository.getReferenceById(id));
    }

    public DetailedPatientDTO update(Long id, PatientUpdateDTO patientUpdateDTO) {
        Patient patient = patientRepository.getReferenceById(id);

        patient.updateInformation(patientUpdateDTO);

        patientRepository.save(patient);

        return new DetailedPatientDTO(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient get(Long id) {
        return patientRepository.getReferenceById(id);
    }
}
