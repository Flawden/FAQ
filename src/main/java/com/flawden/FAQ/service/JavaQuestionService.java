package com.flawden.FAQ.service;

import com.flawden.FAQ.enums.QuestionTypes;
import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.MathQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.repository.QuestionRepository;
import com.flawden.FAQ.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
@Qualifier("javaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random rnd = new Random();

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.save(new JavaQuestion(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question remove(Question question) {
        boolean isSucces = questionRepository.deleteQuestionByQuestionAndQuestionType(question.getQuestion(), QuestionTypes.JAVA.getType());
        if (isSucces) {
            return question;
        } else {
            throw new IllegalArgumentException("Ошибка удаления! Данного вопроса не существует среди вопросов данного типа.");
        }

    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.findQuestionsByQuestionType(QuestionTypes.JAVA.getType());
    }

    @Override
    public Question getRandom() {
        List<Question> questions = questionRepository.findQuestionsByQuestionType(QuestionTypes.JAVA.getType());
        return questions.get(rnd.nextInt(0, questions.size()));
    }

    public long questionsAmount() {
        return questionRepository.findQuestionsByQuestionType(QuestionTypes.JAVA.getType()).size();
    }
}
