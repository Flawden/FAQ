package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    private void add(@RequestParam String question, @RequestParam String answer) {
        if(question.isBlank() || answer.isBlank()) {
            throw new RuntimeException("Ошибка! Значение полей question и answer не могут быть пустыми!");
        }
        questionService.add(question, answer);
    }

    @PostMapping("/add2")
    private void add(@RequestBody Question question) {
        questionService.add(question);
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
    private Question getRandom() {
        return questionService.getRandom();
    }

}
