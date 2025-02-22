package br.com.edgarfreitas.ab.messenger.domain.sms;

import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.exceptions.MailerSendException;
import com.mailersend.sdk.sms.Sms;
import com.mailersend.sdk.sms.SmsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class MailerSendSmsStrategyTest {

    @Autowired
    private MailerSendSmsStrategy mailerSendSmsStrategy;

    private SmsDto smsDto;

    @Mock
    private Sms sms;

    @Mock
    private SmsBuilder smsBuilder;

    @Mock
    private MailerSend mailerSend;

    @BeforeEach
    void Setup() throws MailerSendException {
        smsDto = SmsDto.builder()
                .fromPhoneNumber("5511988888888")
                .toPhoneNumber("5511999999999")
                .name("Unit Test")
                .namePersonalization("Unit Test Personalization")
                .text("This message sent by unit test for app")
                .build();

        when(sms.builder()).thenReturn(smsBuilder);
        when(smsBuilder.send()).thenReturn("123");
        when(smsBuilder.from(anyString())).thenReturn(smsBuilder);
        when(smsBuilder.addRecipient(anyString())).thenReturn(smsBuilder);
        when(smsBuilder.text(anyString())).thenReturn(smsBuilder);
        when(smsBuilder.addPersonalization(anyString(), anyString(), anyString())).thenReturn(smsBuilder);
        when(mailerSend.sms()).thenReturn(sms);
        mailerSendSmsStrategy.setMs(mailerSend);
    }

    @Test
    void SendSuccess() throws MailerSendException {
        boolean success = mailerSendSmsStrategy.Send(smsDto);
        assertTrue(success);
    }

    @Test
    void SendFail() throws MailerSendException {
        when(smsBuilder.send()).thenThrow(MailerSendException.class);
        boolean success = mailerSendSmsStrategy.Send(smsDto);
        assertFalse(success);

        mailerSendSmsStrategy.setMs(null);
        mailerSendSmsStrategy.setToken("xyz");
        success = mailerSendSmsStrategy.Send(smsDto);
        assertFalse(success);
    }
}
