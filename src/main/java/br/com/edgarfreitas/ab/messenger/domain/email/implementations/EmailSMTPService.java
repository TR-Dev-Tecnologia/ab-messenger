package br.com.edgarfreitas.ab.messenger.domain.email.implementations;

import org.springframework.stereotype.Service;

import br.com.edgarfreitas.ab.messenger.domain.email.IEmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

@Service
public class EmailSMTPService implements IEmailService {

    @Override
    public void Send(EmailDto emailDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Send'");
    }

}
