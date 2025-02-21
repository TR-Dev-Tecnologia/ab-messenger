package br.com.edgarfreitas.ab.messenger.domain.email.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailAdress {


    private final String email;
    private final String name;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public EmailAdress(String email, String name) {
        this.name = name;
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("email "+email+" is invalid");
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
