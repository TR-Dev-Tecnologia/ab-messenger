package br.com.edgarfreitas.ab.messenger.domain.sms;

import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.exceptions.MailerSendException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Setter
public class MailerSendSmsStrategy implements ISendSMSStrategy {

    @Value("${settings.mailersend.token}")
    String token;

    private MailerSend ms = null;

    @Override
    public boolean Send(SmsDto smsDto) {
        if (Objects.isNull(ms)) {
            ms = new MailerSend();
        }
        //MailerSend ms = new MailerSend();
        ms.setToken(token);
        try {
            String messageId = ms.sms().builder()
                    .from(smsDto.getFromPhoneNumber())
                    .addRecipient(smsDto.getToPhoneNumber())
                    .text(smsDto.getText())
                    .addPersonalization(
                            smsDto.getToPhoneNumber(),
                            smsDto.getName(),
                            smsDto.getNamePersonalization())
                    .send();

            System.out.println(messageId);
            return true;
        } catch (MailerSendException e) {
            return false;
        }
    }
}
