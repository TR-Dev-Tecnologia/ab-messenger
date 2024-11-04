package br.com.edgarfreitas.ab.messenger.domain.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class EmailDto {
    private String body;
    private String to;
    private String withCopy;
    private String subject;
    private boolean bodyHtml;
}
