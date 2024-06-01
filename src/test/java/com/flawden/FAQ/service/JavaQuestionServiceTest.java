package com.flawden.FAQ.service;

import com.flawden.FAQ.enums.QuestionTypes;
import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.repository.QuestionRepository;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepositoryMock;
    private JavaQuestionService javaQuestionService;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionService(questionRepositoryMock);
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
    public void addByStrings() {
        JavaQuestion javaQuestion = new JavaQuestion("8 Java question", "8 Java answer");
        when(questionRepositoryMock.save(new JavaQuestion("8 Java question", "8 Java answer"))).thenReturn(javaQuestion);
        Assertions.assertEquals(javaQuestion, javaQuestionService.add("8 Java question", "8 Java answer"));
    }

    @Test
    public void addByObject() {
        JavaQuestion javaQuestion = new JavaQuestion("8 Java question", "8 Java answer");
        when(questionRepositoryMock.save(javaQuestion)).thenReturn(javaQuestion);
        Assertions.assertEquals(javaQuestion, javaQuestionService.add(javaQuestion));
    }

    @Test
    public void remove() {
        Question javaQuestion = questions.getFirst();
        when(questionRepositoryMock.deleteQuestionByQuestionAndQuestionType(javaQuestion.getQuestion(), QuestionTypes.JAVA.getType())).thenReturn(true);
        Assertions.assertEquals(javaQuestion, javaQuestionService.remove(javaQuestion));
    }

    @Test
    public void removeWithException() {
        Question javaQuestion = new JavaQuestion("8 Java question", "8 Java answer");
        when(questionRepositoryMock.deleteQuestionByQuestionAndQuestionType(javaQuestion.getQuestion(), QuestionTypes.JAVA.getType())).thenReturn(false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.remove(javaQuestion));
        Assertions.assertEquals("Ошибка удаления! Данного вопроса не существует среди вопросов данного типа.", exception.getMessage());
    }

    @Test
    public void getAll() {
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.JAVA.getType())).thenReturn(questions);
        Assertions.assertEquals(questions, javaQuestionService.getAll());
    }

    @Test
    public void questionsAmount() {
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.JAVA.getType())).thenReturn(questions);
        Assertions.assertEquals(questions.size(), javaQuestionService.questionsAmount());
    }
}
