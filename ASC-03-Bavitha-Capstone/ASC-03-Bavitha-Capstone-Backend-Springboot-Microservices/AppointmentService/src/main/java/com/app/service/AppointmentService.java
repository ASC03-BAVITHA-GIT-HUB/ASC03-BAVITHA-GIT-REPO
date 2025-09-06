// src/main/java/com/app/service/AppointmentService.java
package com.app.service;

import com.app.dto.CreateAppointmentRequest;
import com.app.dto.UpdateAppointmentRequest;
import com.app.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment create(CreateAppointmentRequest req);
    Appointment getById(String id);
    List<Appointment> getAll();
    Appointment update(String id, UpdateAppointmentRequest req);
    void delete(String id);

    List<Appointment> search(String patientId, String doctorId, String status,
                             LocalDateTime from, LocalDateTime to);
}
