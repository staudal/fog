package com.backend.fog.logics;

public class Calculator {

    // NO SHED, WOODS AND ROOF
    public int poleNumber(int length) {
        // NOTE: IF LENGTH OF CARPORT IS LONGER THAN 440 CM (100 + 30 + 310) THEN AN EXTRA POLE IS INSERTED
        // AND IF CARPORT IS LONGER THAN 750 CM (100 + 30 + 310 + 310) THEN TWO EXTRA POLES ARE INSERTED
        int poles = 0;
        if (length >= 0 && length <= 440) {
            poles = 4;
        } else if (length > 440 && length <= 750) {
            poles = 6;
        } else if (length > 750 && length <= 800) {
            poles = 8;
        }
        return poles;
    }
    public int polePrice(int length, int price) {
        return poleNumber(length) * price;
    }

    public int beamNumber(int length) {
        // NOTE: IF THE CARPORT IS LESS THAN OR EQUAL TO 600 CM LONG (LONGEST BEAM AVAILABLE) THEN WE ONLY NEED TWO BEAMS
        // NOTE: IF THE CARPORT IS LONGER THAN 600 CM, WE NEED 4 BEAMS
        int beams = 0;
        if (length >= 0 && length <= 720) {
            beams = 2;
        } else if (length > 720 && length <= 800) {
            beams = 4;
        }
        return beams;
    }
    public int beamPrice(int length, int price) {
        return beamNumber(length) * price;
    }

    public int rafterNumber(int length) {
        // NOTE: NUMBER OF RAFTER IS FOUND BY DIVIDING THE LENGTH WITH THE DESIRED LENGTH BETWEEN EACH RAFTER
        // NOTE: FOR GOOD MEASUREMENT WE ARE ADDING 1 ADDITIONAL RAFTER SO THE CUSTOMER CAN DECIDE THE EXACT LENGTH BETWEEN EACH RAFTER
        return (length / 55) + 1;
    }
    public int rafterPrice(int length, int price) {
        return rafterNumber(length) * price;
    }

    public int lowerSideFasciaNumber(int length) {
        int fascia = 0;
        if (length >= 0 && length <= 600) {
            fascia = 2;
        } else if (length > 600 && length <= 800) {
            fascia = 4;
        }
        return fascia;
    }
    public int lowerSideFasciaPrice(int length, int price) {
        return lowerSideFasciaNumber(length) * price;
    }

    public int upperSideFasciaNumber(int length) {
        int fascia = 0;
        if (length >= 0 && length <= 600) {
            fascia = 2;
        } else if (length > 600 && length <= 800) {
            fascia = 4;
        }
        return fascia;
    }
    public int upperSideFasciaPrice(int length, int price) {
        return upperSideFasciaNumber(length) * price;
    }

    public int lowerFrontBackFasciaNumber() {
        return 2;
    }
    public int lowerFrontBackFasciaPrice(int price) {
        return lowerFrontBackFasciaNumber() * price;
    }

    public int upperFrontBackFasciaNumber() {
        return 2;
    }
    public int upperFrontBackFasciaPrice(int price) {
        return upperFrontBackFasciaNumber() * price;
    }

    public int roofNumber(int length, int width) {
        double widthDouble = width;
        double roof = 0;
        double widthIncludingOverlap = ((widthDouble / 109) * 10) + widthDouble;

        if (length >= 0 && length <= 600) {
            roof = Math.floor((widthIncludingOverlap / 109) + 1);
        } else if (length > 600 & length <= 800) {
            roof = Math.floor(((widthIncludingOverlap / 109) + 1) * 2);
        }
        return (int) roof;
    }
    public int roofPrice(int length, int width, int price) {
        return roofNumber(length, width) * price;
    }

    // NO SHED, BOLTS AND SCREWS
    public int carriageBoltNumber(int length) {
        return poleNumber(length) * 2;
    }
    public int carriageBoltPrice(int length, int price) {
        return carriageBoltNumber(length) * price;
    }

    public int squareWasherNumber(int length) {
        return poleNumber(length) * 2;
    }
    public int squareWasherPrice(int length, int price) {
        return squareWasherNumber(length) * price;
    }

    public int windBracingStrapNumber() {
        return 2;
    }
    public int windBracingStrapPrice(int price) {
        return windBracingStrapNumber() * price;
    }

    public int rafterConnectorLeftNumber(int length) {
        return rafterNumber(length);
    }
    public int rafterConnectorLeftPrice(int length, int price) {
        return rafterConnectorLeftNumber(length) * price;
    }

    public int rafterConnectorRightNumber(int length) {
        return rafterNumber(length);
    }
    public int rafterConnectorRightPrice(int length, int price) {
        return rafterConnectorRightNumber(length) * price;
    }

    public int trapezoidScrewNumber(int width, int length) {
        int numberOfScrews = 0;
        if (width < 200 && length < 300) {
            numberOfScrews = 1;
        } else if (width < 400 && length < 500) {
            numberOfScrews = 2;
        } else {
            numberOfScrews = 3;
        }
        return numberOfScrews;
    }
    public int trapezoidScrewPrice(int width, int length, int price) {
        return trapezoidScrewNumber(width, length) * price;
    }

    public int shedPoleNumber(int shedWidth) {
        int number = 0;
        if (shedWidth == 1) {
            number = 4;
        } else if (shedWidth == 2) {
            number = 3;
        }
        return number;
    }
    public int shedPolePrice(int shedWidth, int price) {
        return shedPoleNumber(shedWidth) * price;
    }

    public int shedJoistSideNumber() {
        return 4;
    }
    public int shedJoistSidePrice(int price) {
        return shedJoistSideNumber() * price;
    }

    public int shedJoistFrontBackNumber(int shedWidth) {
        int number = 0;
        if (shedWidth == 1) {
            number = 12;
        } else if (shedWidth == 2) {
            number = 6;
        }
        return number;
    }
    public int shedJoistFrontBackPrice(int shedWidth, int price) {
        return shedJoistFrontBackNumber(shedWidth) * price;
    }

    public int claddingNumber(int shedWidth, int shedLength, int carportWidth) {
        // overlap with 4 cm per board
        int width = 0;

        // calculating width
        if (shedWidth == 1) {
            width = carportWidth - 70;
        } else {
            width = (carportWidth / 2) - 35;
        }

        int widthInclusiveOverlap = ((width / 10) * 4) + width;
        int lengthInclusiveOverlap = ((shedLength / 10) * 4) + shedLength;

        int claddingSides = ((lengthInclusiveOverlap / 10) + 1) * 2;
        int claddingFrontBack = ((widthInclusiveOverlap / 10) + 1) * 2;

        return claddingSides + claddingFrontBack;
    }
    public int claddingPrice(int shedWidth, int shedLength, int carportWidth, int price) {
        return claddingNumber(shedWidth, shedLength, carportWidth) * price;
    }

    public int joistHingeNumber(int shedWidth) {
        return (shedJoistSideNumber() + shedJoistFrontBackNumber(shedWidth)) * 2;
    }
    public int joistHingePrice(int shedWidth, int price) {
        return joistHingeNumber(shedWidth) * price;
    }
}
