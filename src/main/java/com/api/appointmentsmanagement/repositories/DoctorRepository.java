package com.api.appointmentsmanagement.repositories;

import com.api.appointmentsmanagement.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
