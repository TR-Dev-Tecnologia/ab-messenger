package br.com.edgarfreitas.ab.messenger.domain.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    @Autowired    
    private final ISendMailStrategy sendMailStrategy;

    public void Send(EmailDto emailDto) {
        sendMailStrategy.Send(emailDto);
    }
}
