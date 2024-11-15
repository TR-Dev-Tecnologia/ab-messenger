package br.com.edgarfreitas.ab.messenger.domain.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDto {
    private String body;
    private String to;
    private String toName;
    private String from;
    private String fromName;
    private String withCopy;
    private String subject;
    private boolean bodyHtml;
}
