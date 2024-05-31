package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.AllQuestionsService;
import com.flawden.FAQ.service.interfaces.ExaminerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/exam")
public class ExamController {

    private final ExaminerService examinerService;
    private final AllQuestionsService allQuestionsService;

    public ExamController(ExaminerService examinerService, AllQuestionsService allQuestionsService) {
        this.examinerService = examinerService;
        this.allQuestionsService = allQuestionsService;
    }

    @DeleteMapping("/remove")
    private Question remove(@RequestBody Question question) {
        return allQuestionsService.remove(question);
    }

    @GetMapping
    private Collection<Question> getAll() {
        return allQuestionsService.getAll();
    }

    @GetMapping("/random")
    private Question getRandomQuestion() {
        return allQuestionsService.getRandom();
    }

    @GetMapping("/random-questions")
    private Collection<Question> getRandomQuestions(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }

}
