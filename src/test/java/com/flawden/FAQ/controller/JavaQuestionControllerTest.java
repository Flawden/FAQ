package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.AllQuestionsService;
import com.flawden.FAQ.service.ExaminerServiceImpl;
import com.flawden.FAQ.service.JavaQuestionService;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionControllerTest {

    @Mock
    private JavaQuestionService questionService;
    @Mock
    private ExaminerServiceImpl examinerService;
    private JavaQuestionController javaQuestionController;
    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        javaQuestionController = new JavaQuestionController(questionService, examinerService);
    }

    @BeforeEach
    public void reposInit() {
        questions.add(new JavaQuestion("1 Java question", "1 Java answer"));
        questions.add(new JavaQuestion("2 Java question", "2 Java answer"));
        questions.add(new JavaQuestion("3 Java question", "3 Java answer"));
        questions.add(new JavaQuestion("4 Java question", "4 Java answer"));
        questions.add(new JavaQuestion("5 Java question", "5 Java answer"));
        questions.add(new JavaQuestion("6 Java question", "6 Java answer"));
        questions.add(new JavaQuestion("7 Java question", "7 Java answer"));
    }

    @Test
    public void add() {
        when(questionService.add("1 Java question", "1 Java answer")).thenReturn(questions.getFirst());
        Assertions.assertEquals(questions.getFirst(), javaQuestionController.add("1 Java question", "1 Java answer"));
    }

    @Test
    public void addByObject() {
        JavaQuestion question = (JavaQuestion) questions.getFirst();
        when(questionService.add(question)).thenReturn(question);
        Assertions.assertEquals(questions.getFirst(), javaQuestionController.add(question));
    }

    @Test
    public void remove() {
        JavaQuestion question = (JavaQuestion) questions.getFirst();
        when(questionService.remove(question)).thenReturn(question);
        Assertions.assertEquals(questions.getFirst(), javaQuestionController.remove(question));
    }

    @Test
    public void getAll() {
        when(questionService.getAll()).thenReturn(questions);
        Assertions.assertEquals(questions, javaQuestionController.getAll());
    }

    @Test
    public void getRandomQuestion() {
        when(questionService.getRandom()).thenReturn(questions.getFirst());
        Assertions.assertEquals(questions.getFirst(), javaQuestionController.getRandomQuestion());
    }

}
