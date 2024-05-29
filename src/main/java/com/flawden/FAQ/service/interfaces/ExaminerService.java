package com.flawden.FAQ.service.interfaces;

import com.flawden.FAQ.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
