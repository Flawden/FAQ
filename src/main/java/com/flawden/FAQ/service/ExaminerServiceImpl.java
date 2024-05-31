package com.flawden.FAQ.service;

import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.interfaces.ExaminerService;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionService;

    public ExaminerServiceImpl(List<QuestionService> questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        long questionsAmount = questionService.getFirst().questionsAmount();
        if (questionsAmount < amount) {
            throw new RuntimeException("В базе не хранится столько вопросов.");
        }
        if (questionsAmount == amount) {
            return questionService.getFirst().getAll();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getFirst().getRandom());
        }
        return questions;
    }
}
