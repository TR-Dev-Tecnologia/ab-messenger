package br.com.edgarfreitas.ab.messenger.domain.sms;

import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;

public interface ISendSMSStrategy {
    boolean Send(SmsDto smsDto);
}
