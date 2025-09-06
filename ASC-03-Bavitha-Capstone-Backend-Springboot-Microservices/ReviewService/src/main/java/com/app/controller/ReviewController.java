package com.app.controller;

import com.app.dto.CreateReviewRequest;
import com.app.dto.ReviewDto;
import com.app.entity.Review;
import com.app.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ReviewDto> create(@Valid @RequestBody CreateReviewRequest req) {
        Review r = service.create(req);
        return ResponseEntity.ok(toDto(r));
    }


    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        List<ReviewDto> list = service.getAll()
                .stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Review " + id + " deleted successfully");
    }

    private ReviewDto toDto(Review r) {
        return new ReviewDto(r.getId(), r.getHospitalId(), r.getRating(), r.getComment(), r.getCreatedAt());
    }
}

