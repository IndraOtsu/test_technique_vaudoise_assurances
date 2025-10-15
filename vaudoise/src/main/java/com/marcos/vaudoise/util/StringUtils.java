package com.marcos.vaudoise.util;

import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

@CommonsLog
public class StringUtils {

    public static boolean isValidISO8601Date(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return false;
        }
        DateTimeFormatter dash = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter slash = DateTimeFormatter.ofPattern("uuuu/MM/dd")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(dateString, dash);
            return true;
        } catch (DateTimeParseException ignored) {
            try {
                LocalDate.parse(dateString, slash);
                return true;
            } catch (DateTimeParseException e) {
                log.error("Invalid date format: " + e.getMessage());
                return false;
            }
        }
    }

    public static LocalDate parseToDate(String inputDate) {
        String normalized = inputDate.replace("/", "-");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(normalized, formatter);
    }


    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        return Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
                      .matcher(email)
                      .matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        String phoneRegex = "^\\+[1-9]\\d{6,14}$";
        return Pattern.compile(phoneRegex)
                      .matcher(phone.trim())
                      .matches();
    }
}
