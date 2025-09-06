package com.app.service;

import com.app.dto.CreateReviewRequest;
import com.app.entity.Review;
import com.app.exception.InvalidHospitalIdException;
import com.app.exception.NotFoundException;
import com.app.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public ReviewServiceImpl(ReviewRepository repo) {
        this.repo = repo;
    }

    private String nextReviewId() {
        String prefix = "R";
        String last = repo.findTopByIdStartingWithOrderByIdDesc(prefix)
                .map(Review::getId)
                .orElse(prefix + "0000");
        int next = Integer.parseInt(last.substring(1)) + 1;
        return String.format("%s%04d", prefix, next);
    }


    private boolean hospitalExists(String hospitalId) {
        // Match your Hospital entity's @Table(name="Hospitals")
        String sql = "SELECT COUNT(1) FROM Hospital WHERE HospitalId = :id";
        Query q = entityManager.createNativeQuery(sql).setParameter("id", hospitalId);
        Number n = (Number) q.getSingleResult();
        return n != null && n.longValue() > 0;
    }

    @Override
    @Transactional
    public Review create(CreateReviewRequest req) {
        final String hid = req.getHospitalId().trim();
        if (!hospitalExists(hid)) {
            throw new InvalidHospitalIdException("Invalid hospital id: " + hid);
        }

        Review r = new Review(
                nextReviewId(),
                hid,
                req.getRating(),
                req.getComment().trim(),
                req.getCreatedAt()
        );
        return repo.save(r);
    }

    @Override
    public Review getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Review not found: " + id));
    }

    @Override
    public List<Review> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repo.existsById(id)) throw new NotFoundException("Review not found: " + id);
        repo.deleteById(id);
    }
}

