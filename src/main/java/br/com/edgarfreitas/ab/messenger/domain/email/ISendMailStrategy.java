package br.com.edgarfreitas.ab.messenger.domain.email;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

public interface ISendMailStrategy {
    boolean Send(EmailDto emailDto);
}
