package com.app.controller;

import com.app.dto.CreatePatientRequest;
import com.app.dto.PatientDto;
import com.app.dto.UpdatePatientRequest;
import com.app.entity.Patient;
import com.app.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<PatientDto> create(@Valid @RequestBody CreatePatientRequest req) {
        Patient p = service.create(req);
        return ResponseEntity.ok(toDto(p));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAll() {
        List<PatientDto> list = service.getAll().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable String id, @Valid @RequestBody UpdatePatientRequest req) {
        Patient p = service.update(id, req);
        return ResponseEntity.ok(toDto(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Patient " + id + " deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDto>> search(@RequestParam(required = false) String id,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String city,
                                                   @RequestParam(required = false) String bloodGroup,
                                                   @RequestParam(required = false) String email) {
        List<PatientDto> list = service.search(id, name, city, bloodGroup, email)
                .stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private PatientDto toDto(Patient p) {
        return new PatientDto(
                p.getId(), p.getName(), p.getEmail(), p.getPhone(),
                p.getGender(), p.getAddress(), p.getCity(), p.getBloodGroup(),
                p.getDoctorId()
        );
    }
}
