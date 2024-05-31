package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.ExaminerServiceImpl;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/exam/math")
public class MathQuestionController {

    private final QuestionService questionService;
    private final ExaminerServiceImpl examinerService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService, ExaminerServiceImpl examinerService) {
        this.questionService = questionService;
        this.examinerService = examinerService;
    }

    @PostMapping("/add")
    @ResponseBody
    private Question add(@RequestParam String question, @RequestParam String answer) {
        if(question.isBlank() || answer.isBlank()) {
            throw new RuntimeException("Ошибка! Значение полей question и answer не могут быть пустыми!");
        }

        return questionService.add(question, answer);
    }

    @PostMapping("/add-by-body")
    private Question add(@RequestBody Question question) {
        return questionService.add(question);
    }

    @DeleteMapping("/remove")
    private Question remove(@RequestBody Question question) {
        return questionService.remove(question);
    }

    @GetMapping
    private Collection<Question> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/random")
    private Question getRandomQuestion() {
        return questionService.getRandom();
    }

    @GetMapping("/random-question")
    private Collection<Question> getRandomQuestions(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }

}
