package com.app.controller;

import com.app.dto.CreateDoctorRequest;
import com.app.dto.UpdateDoctorRequest;
import com.app.dto.DoctorDto;
import com.app.entity.Doctor;
import com.app.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/doctors")
@Validated
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<DoctorDto> create(@Valid @RequestBody CreateDoctorRequest req) {
        Doctor d = service.create(req);
        return ResponseEntity.ok(toDto(d));
    }

    //GET all
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAll() {
        List<DoctorDto> list = service.getAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable String id,
                                            @Valid @RequestBody UpdateDoctorRequest req) {
        Doctor d = service.update(id, req);
        return ResponseEntity.ok(toDto(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Doctor " + id + " deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDto>> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String hospitalId,
            @RequestParam(required = false) String email
    ) {
        List<DoctorDto> list = service.search(id, name, specialization, hospitalId, email)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private DoctorDto toDto(Doctor d) {
        return new DoctorDto(d.getId(), d.getName(), d.getEmail(),
                d.getPhone(), d.getSpecialization(),
                d.getHospitalId());
    }
}

