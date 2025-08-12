package com.course.management.Controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController {

    private final MessageSource messageSource;

    public WelcomeController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getWelcomeMessage() {
        return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
    }
}
