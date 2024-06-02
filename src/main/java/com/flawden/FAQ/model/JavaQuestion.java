package com.flawden.FAQ.model;

import com.flawden.FAQ.enums.QuestionTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Java")
public class JavaQuestion extends Question {
    public JavaQuestion() {
        super();
        questionType = "Java";
    }

    public JavaQuestion(String question, String answer) {
        super(question, answer);
        questionType = "Java";
    }

}
