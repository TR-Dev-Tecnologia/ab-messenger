package br.com.edgarfreitas.ab.messenger.domain.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDto {
    private String body;
    private String to;
    private String from;
    private String withCopy;
    private String subject;
    private boolean bodyHtml;
}
