package com.flawden.FAQ.service;

import com.flawden.FAQ.enums.QuestionTypes;
import com.flawden.FAQ.model.JavaQuestion;
import com.flawden.FAQ.model.Question;
import com.flawden.FAQ.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AllQuestionsService {

    private final QuestionRepository questionRepository;
    private final Random rnd = new Random();

    public AllQuestionsService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question remove(Question question) {
        questionRepository.delete(question);
        Optional<Question> isSuccess = questionRepository.findQuestionByQuestion(question);
        if (isSuccess.isEmpty()) {
            return question;
        } else {
            throw new IllegalArgumentException("Ошибка удаления! Данного вопроса не существует среди вопросов данного типа.");
        }
    }

    public Collection<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getRandom() {
        List<Question> questions = questionRepository.findAll();
        return questions.get(rnd.nextInt(0, questions.size()));
    }
}
