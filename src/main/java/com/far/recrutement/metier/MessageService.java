package com.far.recrutement.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Service
public class MessageService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpSession session;

    public String get(String code) {
        return messageSource.getMessage(code, null, (Locale) session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE"));
    }
}
