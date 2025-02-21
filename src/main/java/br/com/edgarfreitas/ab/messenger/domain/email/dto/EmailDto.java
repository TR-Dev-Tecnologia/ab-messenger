package br.com.edgarfreitas.ab.messenger.domain.email.dto;

import br.com.edgarfreitas.ab.messenger.domain.email.vo.EmailAdress;
import br.com.edgarfreitas.ab.messenger.domain.exception.ValidationException;
import lombok.Getter;

import java.util.List;

@Getter
public class EmailDto {
    private final String body;
    private final EmailAdress from;
    private final List<EmailAdress> withCopy;
    private final String subject;
    private final boolean bodyHtml;
    private final List<EmailAdress> recipients;

    public EmailDto(String body, EmailAdress from, List<EmailAdress> withCopy, String subject, boolean bodyHtml, List<EmailAdress> recipients) throws ValidationException {
        this.body = body;
        this.from = from;
        this.withCopy = withCopy;
        this.subject = subject;
        this.bodyHtml = bodyHtml;
        this.recipients = recipients;
        this.validate();
    }

    private void validate() throws ValidationException {
        if ((body == null) || (body.isEmpty()))
            throw new ValidationException("Body is mandatory");

        if ((subject == null) || (subject.isEmpty()))
            throw new ValidationException("Subject is mandatory");

        if ((recipients == null) || (recipients.isEmpty()))
            throw new ValidationException("Recipients is mandatory");

        if ((from == null) || from.getEmail() == null || (from.getEmail().isEmpty()))
            throw new ValidationException("From is mandatory");
    }


}
