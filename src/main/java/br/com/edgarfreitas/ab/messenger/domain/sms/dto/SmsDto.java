package br.com.edgarfreitas.ab.messenger.domain.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDto {
    private String fromPhoneNumber;
    private String toPhoneNumber;
    private String name;
    private String namePersonalization;
    private String text;
}
