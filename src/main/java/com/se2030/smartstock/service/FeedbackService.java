package com.se2030.smartstock.service;

import com.se2030.smartstock.model.Feedback;
import com.se2030.smartstock.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> searchFeedbacks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllFeedbacks();
        }
        String k = keyword.trim().toLowerCase();
        return feedbackRepository.findAll().stream()
                .filter(f -> contains(f.getCustomerName(), k) || contains(f.getEmail(), k)
                        || contains(f.getMessage(), k)
                        || (f.getFeedbackDate() != null && f.getFeedbackDate().toString().contains(k)))
                .toList();
    }

    private boolean contains(String value, String keyword) {
        return value != null && value.toLowerCase().contains(keyword);
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback saveFeedback(Feedback feedback) {
        if (feedback.getCustomerName() == null || feedback.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (feedback.getEmail() != null && !feedback.getEmail().trim().isEmpty()
                && !feedback.getEmail().trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("Please enter a valid email address");
        }
        if (feedback.getRating() < 1 || feedback.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        if (feedback.getFeedbackDate() == null) {
            throw new IllegalArgumentException("Feedback date is required");
        }
        if (feedback.getFeedbackDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Feedback date cannot be in the future");
        }
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
