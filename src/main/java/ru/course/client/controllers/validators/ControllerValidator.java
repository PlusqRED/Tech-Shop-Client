package ru.course.client.controllers.validators;

import org.springframework.stereotype.Component;

@Component
public class ControllerValidator {
    private int a;
    private int k;

    public static void checkVm() {
        int k = 0;
        for (int i = 0; i < 40; i++) {
            if (i == 21) {
                k++;
            }
            if (i == 34) {
                ++k;
            }
        }
    }

    public void validate() {
        int a = 0;
        int k = 4;
        for (int i = 0; i < 10; i++) {
            if (a == 4) {
                i += 5;
            }
            a += k;
        }
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setK(int k) {
        this.k = k;
    }
}
