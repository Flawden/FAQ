package com.flawden.FAQ.service;

import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.interfaces.ExaminerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        long questionsAmount = javaQuestionService.questionsAmount();
        if (questionsAmount < amount) {
            throw new RuntimeException("В базе не хранится столько вопросов.");
        }
        if (questionsAmount == amount) {
            return javaQuestionService.getAll();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(javaQuestionService.getRandom());
        }
        return null;
    }
}
