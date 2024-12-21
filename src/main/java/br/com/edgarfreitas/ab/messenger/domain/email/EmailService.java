package br.com.edgarfreitas.ab.messenger.domain.email;

import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    @Autowired    
    private final ISendMailStrategy sendMailStrategy;

    public ResonseDto Send(EmailDto emailDto) {
        boolean success = sendMailStrategy.Send(emailDto);
        return new ResonseDto(success, success ? "E-Mail sent" : "Fail on send");
    }
}
