package com.app.controller;

import com.app.dto.CreateHospitalRequest;
import com.app.dto.HospitalDto;
import com.app.entity.Hospital;
import com.app.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/hospitals")
@Validated
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<HospitalDto> create(@Valid @RequestBody CreateHospitalRequest req) {
        Hospital h = service.create(req);
        return ResponseEntity.ok(toDto(h));
    }

    // VIEW all
    @GetMapping
    public ResponseEntity<List<HospitalDto>> getAll() {
        List<HospitalDto> list = service.getAll().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // VIEW by id
    @GetMapping("/{id}")
    public ResponseEntity<HospitalDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<HospitalDto>> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city
    ) {
        List<HospitalDto> list = service.search(id, name, city).stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Hospital " + id + " deleted successfully");
    }

    private HospitalDto toDto(Hospital h) {
        return new HospitalDto(h.getId(), h.getName(), h.getEmail(), h.getPhone(), h.getAddress(), h.getCity());
    }
}

