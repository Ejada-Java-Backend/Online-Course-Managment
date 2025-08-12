package com.course.management;

import com.course.management.Controller.WelcomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomeControllerTest {

    private WelcomeController welcomeController;

    @BeforeEach
    public void setUp() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");

        welcomeController = new WelcomeController(messageSource);
    }

    @Test
    public void testWelcomeMessageInEnglish() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        String message = welcomeController.getWelcomeMessage();
        assertEquals("Welcome to the Course Management System!", message);
    }

    @Test
    public void testWelcomeMessageInArabic() {
        LocaleContextHolder.setLocale(new Locale("ar"));
        String message = welcomeController.getWelcomeMessage();
        assertEquals("مرحبًا بك في نظام إدارة الدورات!", message);
    }
}
