package com.backend.fog.errors;

public class ErrorHandler {
    public String errorClass() {
        return "is-invalid";
    }

    public String emailNotFound() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email doesn't exist. Try again.\n" +
                "      </div>";
    }

    public String wrongPassword() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Incorrect password. Try again.\n" +
                "      </div>";
    }

    public String emailAreadyExists() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email already exists. Try again.\n" +
                "      </div>";
    }

    public String discountPriceTooLow() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Tilbudsprisen skal være højere end indkøbsprisen.\n" +
                "      </div>";
    }
}
