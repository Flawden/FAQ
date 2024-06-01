package com.flawden.FAQ.service;

import com.flawden.FAQ.enums.QuestionTypes;
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
public class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepositoryMock;
    private MathQuestionService mathQuestionService;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mathQuestionService = new MathQuestionService(questionRepositoryMock);
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
    public void addByStrings() {
        MathQuestion mathQuestion = new MathQuestion("8 Math question", "8 Math answer");
        when(questionRepositoryMock.save(new MathQuestion("8 Math question", "8 Math answer"))).thenReturn(mathQuestion);
        Assertions.assertEquals(mathQuestion, mathQuestionService.add("8 Math question", "8 Math answer"));
    }

    @Test
    public void addByObject() {
        MathQuestion mathQuestion = new MathQuestion("8 Math question", "8 Math answer");
        when(questionRepositoryMock.save(mathQuestion)).thenReturn(mathQuestion);
        Assertions.assertEquals(mathQuestion, mathQuestionService.add(mathQuestion));
    }

    @Test
    public void remove() {
        Question mathQuestion = questions.getFirst();
        when(questionRepositoryMock.deleteQuestionByQuestionAndQuestionType(mathQuestion.getQuestion(), QuestionTypes.MATH.getType())).thenReturn(true);
        Assertions.assertEquals(mathQuestion, mathQuestionService.remove(mathQuestion));
    }

    @Test
    public void removeWithException() {
        Question mathQuestion = new MathQuestion("8 Math question", "8 Math answer");
        when(questionRepositoryMock.deleteQuestionByQuestionAndQuestionType(mathQuestion.getQuestion(), QuestionTypes.MATH.getType())).thenReturn(false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionService.remove(mathQuestion));
        Assertions.assertEquals("Ошибка удаления! Данного вопроса не существует среди вопросов данного типа.", exception.getMessage());
    }

    @Test
    public void getAll() {
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.MATH.getType())).thenReturn(questions);
        Assertions.assertEquals(questions, mathQuestionService.getAll());
    }

    @Test
    public void questionsAmount() {
        when(questionRepositoryMock.findQuestionsByQuestionType(QuestionTypes.MATH.getType())).thenReturn(questions);
        Assertions.assertEquals(questions.size(), mathQuestionService.questionsAmount());
    }
}
