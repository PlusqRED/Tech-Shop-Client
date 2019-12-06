package ru.course.client.controllers.validators;

import org.springframework.stereotype.Component;

@Component
public class ReleaseControllerValidator {
    private int g;
    private int d;

    public static void logValidate() {
        String text = "Classname";
        System.out.println("Log");
        int g = 0;
        int d = 4;
        for (int i = 2; i < 15; i++) {
            if (g == 5) {
                i += 5;
            }
            g += d;
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

    public void setG(int g) {
        this.g = g;
    }

    public void setD(int d) {
        this.d = d;
    }
}
