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

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionRepository questionRepositoryMock;
    private ExaminerServiceImpl examinerService;
    private List<QuestionService> questionServices = new ArrayList<>();
    private MathQuestionService mathQuestionService;
    private JavaQuestionService javaQuestionService;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mathQuestionService = new MathQuestionService(questionRepositoryMock);
        javaQuestionService = new JavaQuestionService(questionRepositoryMock);
        questionServices.add(mathQuestionService);
        questionServices.add(javaQuestionService);
        examinerService = new ExaminerServiceImpl(questionServices, mathQuestionService, javaQuestionService, questionRepositoryMock);
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
    public void getQuestionsWithExceptions() {
        when(questionRepositoryMock.findAll()).thenReturn(questions);
        IllegalArgumentException exceptionVariablesEqualsOrAboveZero = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(0));
        IllegalArgumentException exceptionVariablesEqualsOrAboveZeroAnotherOne = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(-10));
        RuntimeException exceptionWithSmallSizeOfBD = Assertions.assertThrows(RuntimeException.class, () -> examinerService.getQuestions(questions.size() + 1));
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZero.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZeroAnotherOne.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionWithSmallSizeOfBD.getMessage(), "В базе не хранится столько вопросов.");

    }

    @Test
    public void getMathQuestionsWithExceptions() {
        List<Question> mathQuestions = questions.stream().filter(question -> question.getQuestionType().equals(QuestionTypes.MATH.getType())).toList();
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.MATH.getType())).thenReturn(mathQuestions);
        IllegalArgumentException exceptionVariablesEqualsOrAboveZero = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getMathQuestions(0));
        IllegalArgumentException exceptionVariablesEqualsOrAboveZeroAnotherOne = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getMathQuestions(-10));
        RuntimeException exceptionWithSmallSizeOfBD = Assertions.assertThrows(RuntimeException.class, () -> examinerService.getMathQuestions(questions.size() + 1));
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZero.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZeroAnotherOne.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionWithSmallSizeOfBD.getMessage(), "В базе не хранится столько вопросов.");
    }

    @Test
    public void getJavaQuestionsWithExceptions() {
        List<Question> javaQuestions = questions.stream().filter(question -> question.getQuestionType().equals(QuestionTypes.MATH.getType())).toList();
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.JAVA.getType())).thenReturn(javaQuestions);
        IllegalArgumentException exceptionVariablesEqualsOrAboveZero = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getJavaQuestions(0));
        IllegalArgumentException exceptionVariablesEqualsOrAboveZeroAnotherOne = Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getJavaQuestions(-10));
        RuntimeException exceptionWithSmallSizeOfBD = Assertions.assertThrows(RuntimeException.class, () -> examinerService.getJavaQuestions(questions.size() + 1));
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZero.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionVariablesEqualsOrAboveZeroAnotherOne.getMessage(), "Значение не может быть ниже или равно нулю.");
        Assertions.assertEquals(exceptionWithSmallSizeOfBD.getMessage(), "В базе не хранится столько вопросов.");
    }
}
