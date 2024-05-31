package com.flawden.FAQ.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Math")
public class MathQuestion extends Question {

    public MathQuestion() {
        super();
        questionType = "Math";
    }

    public MathQuestion(String question, String answer) {
        super(question, answer);
        questionType = "Math";
    }

}
