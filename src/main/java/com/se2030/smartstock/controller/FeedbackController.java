package com.se2030.smartstock.controller;

import com.se2030.smartstock.model.Feedback;
import com.se2030.smartstock.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public String listFeedbacks(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("feedbacks", feedbackService.searchFeedbacks(keyword));
        model.addAttribute("keyword", keyword);
        return "feedback/list";
    }

    @GetMapping("/new")
    public String newFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback/form";
    }

    @GetMapping("/edit/{id}")
    public String editFeedbackForm(@PathVariable Long id, Model model) {
        model.addAttribute("feedback", feedbackService.getFeedbackById(id));
        return "feedback/form";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute Feedback feedback, Model model) {
        try {
            feedbackService.saveFeedback(feedback);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "feedback/form";
        }
        return "redirect:/feedbacks";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/feedbacks";
    }
}
