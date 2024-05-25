package com.api.appointmentsmanagement.services;

import com.api.appointmentsmanagement.controllers.dtos.AppointmentListDTO;
import com.api.appointmentsmanagement.controllers.dtos.AppointmentRegisterDTO;
import com.api.appointmentsmanagement.controllers.dtos.AppointmentUpdateDTO;
import com.api.appointmentsmanagement.controllers.dtos.DetailedAppointmentDTO;
import com.api.appointmentsmanagement.models.Appointment;
import com.api.appointmentsmanagement.models.Doctor;
import com.api.appointmentsmanagement.models.Patient;
import com.api.appointmentsmanagement.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public AppointmentListDTO create(AppointmentRegisterDTO appointmentRegisterDTO) {
        Doctor doctor = doctorService.get(appointmentRegisterDTO.doctorId());

        Patient patient = patientService.get(appointmentRegisterDTO.patientId());

        Appointment appointment = new Appointment(appointmentRegisterDTO);

        appointment.setDoctor(doctor);

        appointment.setPatient(patient);

        return new AppointmentListDTO(appointmentRepository.save(appointment));
    }

    public Page<AppointmentListDTO> find(Pageable pagination) {
        return appointmentRepository.findAll(pagination).map(AppointmentListDTO::new);
    }

    public DetailedAppointmentDTO findById(Long id) {
        return new DetailedAppointmentDTO(appointmentRepository.getReferenceById(id));
    }

    public DetailedAppointmentDTO update(Long id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.getReferenceById(id);

        appointment.updateInformation(appointmentUpdateDTO);

        if (appointmentUpdateDTO.doctorId() != null) {
            Doctor doctor = doctorService.get(appointmentUpdateDTO.doctorId());

            appointment.setDoctor(doctor);
        }

        appointmentRepository.save(appointment);

        return new DetailedAppointmentDTO(appointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
