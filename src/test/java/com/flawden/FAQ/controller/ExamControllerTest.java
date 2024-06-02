package com.flawden.FAQ.controller;

import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.service.AllQuestionsService;
import com.flawden.FAQ.service.ExaminerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamControllerTest {

    @Mock
    private AllQuestionsService allQuestionsServiceMock;

    @Mock
    private ExaminerServiceImpl examinerServiceMock;
    private ExamController examController;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        examController = new ExamController(examinerServiceMock, allQuestionsServiceMock);
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
        questions.add(new MathQuestion("1 Math question", "1 Math answer"));
        questions.add(new MathQuestion("2 Math question", "2 Math answer"));
        questions.add(new MathQuestion("3 Math question", "3 Math answer"));
        questions.add(new MathQuestion("4 Math question", "4 Math answer"));
        questions.add(new MathQuestion("5 Math question", "5 Math answer"));
        questions.add(new MathQuestion("6 Math question", "6 Math answer"));
        questions.add(new MathQuestion("7 Math question", "7 Math answer"));
    }

    @Test
    public void remove() {
        Question questionJava = new JavaQuestion("JavaQ", "JavaA");
        Question questionMath = new MathQuestion("MathQ", "MathA");
        when(allQuestionsServiceMock.remove(questionJava)).thenReturn(questionJava);
        Assertions.assertEquals(questionJava, examController.remove(questionJava));
        when(allQuestionsServiceMock.remove(questionMath)).thenReturn(questionMath);
        Assertions.assertEquals(questionMath, examController.remove(questionMath));
    }

    @Test
    public void getAll() {
        when(allQuestionsServiceMock.getAll()).thenReturn(questions);
        Assertions.assertEquals(questions, examController.getAll());
    }

    @Test
    public void getRandomQuestion() {
        when(allQuestionsServiceMock.getRandom()).thenReturn(questions.getFirst());
        Assertions.assertEquals(questions.getFirst(), examController.getRandomQuestion());
    }

}
