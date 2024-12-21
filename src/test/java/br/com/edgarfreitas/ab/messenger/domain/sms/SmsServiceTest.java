package br.com.edgarfreitas.ab.messenger.domain.sms;

import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SmsServiceTest {

    @Mock
    private ISendSMSStrategy sendSMSStrategy;

    private SmsService smsService;
    private SmsDto smsDto;

    @BeforeEach
    void Setup() {
        smsService = new SmsService(sendSMSStrategy);
        smsDto = SmsDto.builder()
                .fromPhoneNumber("5511988888888")
                .toPhoneNumber("5511999999999")
                .name("Unit Test")
                .namePersonalization("Unit Test Personalization")
                .text("This message sent by unit test for app")
                .build();
    }

    @Test
    void SendSuccessOK() {
        when(sendSMSStrategy.Send(any())).thenReturn(true);
        ResonseDto resonseDto = smsService.Send(smsDto);
        assertTrue(resonseDto.success());
        assertEquals("SMS sent", resonseDto.message());
    }

    @Test
    void SendFail() {
        when(sendSMSStrategy.Send(any())).thenReturn(false);
        ResonseDto resonseDto = smsService.Send(smsDto);
        assertFalse(resonseDto.success());
        assertEquals("Fail on send", resonseDto.message());
    }
}
