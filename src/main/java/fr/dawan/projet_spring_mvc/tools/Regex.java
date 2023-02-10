package fr.dawan.projet_spring_mvc.tools;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Regex {

    public static boolean regexEmail(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",email);
    }

    public static boolean regexPassword(String password) {
        if (password.equals("") || password == null) {
            return false;
        }
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,32}$",password);
    }

    public static boolean regexName(String name) {
        if (name == null) {
            return false;
        }
        return Pattern.matches("^[\\s,.'\\-\\pL]+$",name);
    }
}
