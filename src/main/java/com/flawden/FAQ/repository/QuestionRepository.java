package com.flawden.FAQ.repository;

import com.flawden.FAQ.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
    public interface QuestionRepository extends JpaRepository<Question, Long> {

    ArrayList<Question> findQuestionsByQuestionType(String type);
    Question findQuestionByQuestionType(String type);
}
