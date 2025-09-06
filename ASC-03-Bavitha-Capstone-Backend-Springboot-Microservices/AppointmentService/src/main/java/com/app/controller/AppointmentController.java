package com.app.controller;

import com.app.dto.AppointmentDto;
import com.app.dto.CreateAppointmentRequest;
import com.app.dto.UpdateAppointmentRequest;
import com.app.entity.Appointment;
import com.app.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
@Validated
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> create(@Valid @RequestBody CreateAppointmentRequest req) {
        Appointment a = service.create(req);
        return ResponseEntity.ok(toDto(a));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAll() {
        List<AppointmentDto> list = service.getAll().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> update(@PathVariable String id,
                                                 @Valid @RequestBody UpdateAppointmentRequest req) {
        Appointment a = service.update(id, req);
        return ResponseEntity.ok(toDto(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Appointment " + id + " deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDto>> search(
            @RequestParam(required = false) String patientId,
            @RequestParam(required = false) String doctorId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        List<AppointmentDto> list = service.search(patientId, doctorId, status, from, to)
                .stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private AppointmentDto toDto(Appointment a) {
        return new AppointmentDto(a.getId(), a.getPatientId(), a.getDoctorId(),
                a.getWhenAt(), a.getReason(), a.getStatus());
    }
}

