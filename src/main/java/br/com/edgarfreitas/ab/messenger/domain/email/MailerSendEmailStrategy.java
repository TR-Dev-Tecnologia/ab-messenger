package br.com.edgarfreitas.ab.messenger.domain.email;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Attachment;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;

@Component
public class MailerSendEmailStrategy implements ISendMailStrategy {

    @Value("${settings.mailersend.token}")
    String token;

    @Value("${settings.mailersend.domain}")
    String domain;


    @Override
    public boolean Send(EmailDto emailDto) {

        Email email = new Email();
    
        email.setFrom(emailDto.getFrom().getName(), "noreply@"+domain);
        emailDto.getRecipients().forEach( emailAdress -> {
            email.addRecipient(emailAdress.getName(), emailAdress.getEmail());
        });

    
        email.setSubject(emailDto.getSubject());

        if (emailDto.isBodyHtml()) {
            email.setHtml(emailDto.getBody());
        } else {
            email.setPlain(emailDto.getBody());            
        }

        MailerSend ms = new MailerSend();

        ms.setToken(token);

        email.attachments.add(new Attachment());

        try {
            MailerSendResponse response = ms.emails().send(email); 
            return (response.responseStatusCode == 202);            
        } catch (MailerSendException e) {            
            e.printStackTrace();
            return false;
        }              
    }

}
