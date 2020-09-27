package com.redditclone.service;

import org.thymeleaf.context.Context;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {

	private final TemplateEngine templateEngine;

    String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("EmailTemplate", context);
    }
}
