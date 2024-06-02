package com.flawden.FAQ.service;

import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.repository.QuestionRepository;
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
public class AllQuestionsServiceTest {

    @Mock
    private QuestionRepository questionRepositoryMock;
    private AllQuestionsService allQuestionsService;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        allQuestionsService = new AllQuestionsService(questionRepositoryMock);
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
        Question javaQuestion = questions.getFirst();
        Question mathQuestion = questions.getLast();
        Assertions.assertEquals(javaQuestion, allQuestionsService.remove(javaQuestion));
        Assertions.assertEquals(mathQuestion, allQuestionsService.remove(mathQuestion));
    }

    @Test
    public void getAll() {
        when(questionRepositoryMock.findAll()).thenReturn(questions);
        Assertions.assertEquals(questions, allQuestionsService.getAll());
    }

}
