package com.flawden.FAQ.enums;

import lombok.Getter;

@Getter
public enum QuestionTypes {

    JAVA("Java"), MATH("Math");

    private final String type;

    QuestionTypes(String type) {
        this.type = type;
    }

}
