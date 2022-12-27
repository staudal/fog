package com.backend.fog.errors;

public class ErrorHandler {
    public static String errorClass() {
        return "is-invalid";
    }

    public static String emailNotFound() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email eksisterer ikke. Prøv igen.\n" +
                "      </div>";
    }

    public static String wrongPassword() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Forkert adgangskode. Prøv igen.\n" +
                "      </div>";
    }

    public static String emailAreadyExists() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email eksisterer allerede. Prøv igen.\n" +
                "      </div>";
    }

    public static String widthNotDefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst en bredde på carporten.\n" +
                "      </div>";
    }

    public static String lengthNotDefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst en længde på carporten.\n" +
                "      </div>";
    }

    public static String proportionsErrorMessage() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Skuret kan ikke være længere end carporten.\n" +
                "      </div>";
    }

    public static String shedWidthUndefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst både skurets længde og bredde.\n" +
                "      </div>";
    }

    public static String shedLengthUndefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst både skurets længde og bredde.\n" +
                "      </div>";
    }
}
