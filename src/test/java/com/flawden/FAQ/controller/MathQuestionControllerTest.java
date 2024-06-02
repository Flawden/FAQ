package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.ExaminerServiceImpl;
import com.flawden.FAQ.service.JavaQuestionService;
import com.flawden.FAQ.service.MathQuestionService;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionControllerTest {

    @Mock
    private MathQuestionService questionService;
    @Mock
    private ExaminerServiceImpl examinerService;
    private MathQuestionController mathQuestionController;
    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mathQuestionController = new MathQuestionController(questionService, examinerService);
    }

    @BeforeEach
    public void reposInit() {
        questions.add(new MathQuestion("1 Math question", "1 Math answer"));
        questions.add(new MathQuestion("2 Math question", "2 Math answer"));
        questions.add(new MathQuestion("3 Math question", "3 Math answer"));
        questions.add(new MathQuestion("4 Math question", "4 Math answer"));
        questions.add(new MathQuestion("5 Math question", "5 Math answer"));
        questions.add(new MathQuestion("6 Math question", "6 Math answer"));
        questions.add(new MathQuestion("7 Math question", "7 Math answer"));
    }

    @Test
    public void add() {
        when(questionService.add("1 Java question", "1 Java answer")).thenReturn(questions.getFirst());
        Assertions.assertEquals(questions.getFirst(), mathQuestionController.add("1 Java question", "1 Java answer"));
    }

    @Test
    public void addByObject() {
        MathQuestion question = (MathQuestion) questions.getFirst();
        when(questionService.add(question)).thenReturn(question);
        Assertions.assertEquals(questions.getFirst(), mathQuestionController.add(question));
    }

    @Test
    public void remove() {
        MathQuestion question = (MathQuestion) questions.getFirst();
        when(questionService.remove(question)).thenReturn(question);
        Assertions.assertEquals(questions.getFirst(), mathQuestionController.remove(question));
    }

    @Test
    public void getAll() {
        when(questionService.getAll()).thenReturn(questions);
        Assertions.assertEquals(questions, mathQuestionController.getAll());
    }

    @Test
    public void getRandomQuestion() {
        when(questionService.getRandom()).thenReturn(questions.getFirst());
        Assertions.assertEquals(questions.getFirst(), mathQuestionController.getRandomQuestion());
    }

}
