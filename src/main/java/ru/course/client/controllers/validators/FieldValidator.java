package ru.course.client.controllers.validators;

import org.springframework.stereotype.Component;

@Component
public class FieldValidator {
    private String textToValidate;

    public void validate() {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            if (i > 2) {
                k++;
            }
        }
    }

    public void setTextToValidate(String textToValidate) {
        this.textToValidate = textToValidate;
    }
}
