package com.flawden.FAQ.service;

import com.flawden.FAQ.enums.QuestionTypes;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.repository.QuestionRepository;
import com.flawden.FAQ.service.interfaces.ExaminerService;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices;
    private final MathQuestionService mathQuestionService;
    private final JavaQuestionService javaQuestionService;
    private final QuestionRepository questionRepository;
    private final Random rnd = new Random();

    public ExaminerServiceImpl(List<QuestionService> questionServices, MathQuestionService mathQuestionService, JavaQuestionService javaQuestionService, QuestionRepository questionRepository) {
        this.questionServices = questionServices;
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
        this.questionRepository = questionRepository;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Значение не может быть ниже или равно нулю.");
        }
        List<Question> questions = questionRepository.findAll();
        long questionsAmount = questions.size();;
        if (questionsAmount < amount) {
            throw new RuntimeException("В базе не хранится столько вопросов.");
        }
        if (questionsAmount == amount) {
            return questions;
        }
        Set<Question> questionsToReturn = new HashSet<>();
        while (questionsToReturn.size() < amount) {
            questionsToReturn.add(questionServices.get(rnd.nextInt(0, questionServices.size())).getRandom());
        }
        return questionsToReturn;
    }

    public Collection<Question> getMathQuestions(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Значение не может быть ниже или равно нулю.");
        }
        List<Question> questions = questionRepository.findQuestionsByQuestionType(QuestionTypes.MATH.getType());
        long questionsAmount = questions.size();;
        if (questionsAmount < amount) {
            throw new RuntimeException("В базе не хранится столько вопросов.");
        }
        if (questionsAmount == amount) {
            return questions;
        }
        Set<Question> questionsToReturn = new HashSet<>();
        while (questionsToReturn.size() < amount) {
            questionsToReturn.add(mathQuestionService.getRandom());
        }
        return questionsToReturn;
    }

    public Collection<Question> getJavaQuestions(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Значение не может быть ниже или равно нулю.");
        }
        List<Question> questions = questionRepository.findQuestionsByQuestionType(QuestionTypes.JAVA.getType());
        long questionsAmount = questions.size();;
        if (questionsAmount < amount) {
            throw new RuntimeException("В базе не хранится столько вопросов.");
        }
        if (questionsAmount == amount) {
            return questions;
        }
        Set<Question> questionsToReturn = new HashSet<>();
        while (questionsToReturn.size() < amount) {
            questionsToReturn.add(javaQuestionService.getRandom());
        }
        return questionsToReturn;
    }
}
