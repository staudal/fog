package com.backend.fog.logics;

public class Calculator {

    public int calculateNumberOfPoles(int length, int width) {
        int numberOfPoles = 0;

        // LENGTH:  0 - 310
        // WIDTH:   0 - 500
        if (length >= 0 && length <= 310 && width >= 0 && width <= 500) {
            numberOfPoles = 4;
        }

        // LENGTH:  311 - 620
        // WIDTH:   0 - 500
        if (length >= 311 && length <= 620 && width >= 0 && width <= 500) {
            numberOfPoles = 6;
        }

        // LENGTH:  621 - 800
        // WIDTH:   0 - 500
        if (length >= 621 && length <= 800 && width >= 0 && width <= 500) {
            numberOfPoles = 8;
        }

        // LENGTH:  0 - 310
        // WIDTH:   501 - 600
        if (length >= 0 && length <= 310 && width >= 501 && width <= 600) {
            numberOfPoles = 6;
        }

        // LENGTH:  311 - 620
        // WIDTH:   501 - 600
        if (length >= 311 && length <= 620 && width >= 501 && width <= 600) {
            numberOfPoles = 9;
        }

        // LENGTH:  621 - 800
        // WIDTH:   501 - 600
        if (length >= 621 && length <= 800 && width >= 501 && width <= 600) {
            numberOfPoles = 12;
        }

        return numberOfPoles;
    }

    public int calculatePriceOfPoles(int carportLength, int carportWidth, int price) {
        return (calculateNumberOfPoles(carportLength, carportWidth) * price);
    }

    public int calculateNumberOfSupportBeam(int length, int width) {
        int numberOfSupportBeams = 0;

        // LENGTH:  0 - 310
        // WIDTH:   0 - 500
        if (length >= 0 && length <= 310 && width >= 0 && width <= 500) {
            numberOfSupportBeams = 2;
        }

        // LENGTH:  311 - 620
        // WIDTH:   0 - 500
        if (length >= 311 && length <= 620 && width >= 0 && width <= 500) {
            numberOfSupportBeams = 4;
        }

        // LENGTH:  621 - 800
        // WIDTH:   0 - 500
        if (length >= 621 && length <= 800 && width >= 0 && width <= 500) {
            numberOfSupportBeams = 6;
        }

        // LENGTH:  0 - 310
        // WIDTH:   501 - 600
        if (length >= 0 && length <= 310 && width >= 501 && width <= 600) {
            numberOfSupportBeams = 3;
        }

        // LENGTH:  311 - 620
        // WIDTH:   501 - 600
        if (length >= 311 && length <= 620 && width >= 501 && width <= 600) {
            numberOfSupportBeams = 6;
        }

        // LENGTH:  621 - 800
        // WIDTH:   501 - 600
        if (length >= 621 && length <= 800 && width >= 501 && width <= 600) {
            numberOfSupportBeams = 9;
        }

        return numberOfSupportBeams;
    }

    public int calculatePriceOfSupportBeams(int carportLength, int carportWidth, int price) {
        return (calculateNumberOfSupportBeam(carportLength, carportWidth) * price);
    }

    public int calculateNumberOfRafters(int length) {
        double numberOfRafters = 0;
        numberOfRafters = Math.floor(length / 55) + 1;
        return (int) numberOfRafters;
    }

    public int calculatePriceOfRafters(int length, int price) {
        return (calculateNumberOfRafters(length) * price);
    }
}
