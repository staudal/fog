package com.backend.fog.errors;

public class ErrorHandler {
    public String errorClass() {
        return "is-invalid";
    }

    public String emailNotFound() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email eksisterer ikke. Prøv igen.\n" +
                "      </div>";
    }

    public String wrongPassword() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Forkert adgangskode. Prøv igen.\n" +
                "      </div>";
    }

    public String emailAreadyExists() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Email eksisterer allerede. Prøv igen.\n" +
                "      </div>";
    }

    public String discountPriceTooLow() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Tilbudsprisen skal være højere end indkøbsprisen.\n" +
                "      </div>";
    }

    public String widthNotDefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst en bredde på carporten.\n" +
                "      </div>";
    }

    public String lengthNotDefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst en længde på carporten.\n" +
                "      </div>";
    }

    public String proportionsErrorMessage() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Skuret kan ikke være længere end carporten.\n" +
                "      </div>";
    }

    public String shedWidthUndefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst både skurets længde og bredde.\n" +
                "      </div>";
    }

    public String shedLengthUndefined() {
        return "<div class=\"invalid-tooltip\">\n" +
                "        Angiv venligst både skurets længde og bredde.\n" +
                "      </div>";
    }
}
