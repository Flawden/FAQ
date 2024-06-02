package com.flawden.FAQ.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Entity
@Getter
@Setter
@Inheritance
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"question"}) )
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected long id;

    @Column
    protected String question;

    @Column
    protected String answer;

    @Column
    protected String questionType;

    public Question() {
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return "Вопрос: " + question + "\n" +
                "Ответ: " + answer;
    }
}
