package br.com.edgarfreitas.ab.messenger.domain.sms;

import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsService {

    @Autowired
    private ISendSMSStrategy sendSMSStrategy;

    public ResonseDto Send(SmsDto smsDto) {
        boolean success = sendSMSStrategy.Send(smsDto);
        return new ResonseDto(success, success ? "SMS sent" : "Fail on send");
    }
}
