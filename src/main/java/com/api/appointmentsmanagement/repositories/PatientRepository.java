package com.api.appointmentsmanagement.repositories;

import com.api.appointmentsmanagement.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
