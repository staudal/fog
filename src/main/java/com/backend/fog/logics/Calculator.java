package com.backend.fog.logics;

public class Calculator {

    public int calculateNumberOfBeams(int length, int width) {
        double numberOfBeams = 0;

        // if carport is max 310 cm long and 500 cm wide
        if (length <= 310 && width <= 500) {
            numberOfBeams = 4;
        }

        // if carport is max 500 cm wide and longer than 310 cm
        if (width <= 500 && length > 310) {
            numberOfBeams = Math.floor(length / 310) + Math.floor(length / 310) + 4;
        }

        // if carport is max 310 cm long and wider than 500 cm
        if (width > 500 && length <= 310) {
            numberOfBeams = Math.floor(width / 500) + Math.floor(width / 500) + 4;
        }

        // if carport is both wider and longer than 310 cm
        if (width > 500 && length > 310) {
            double extraMiddleBeams = Math.floor(width / 500);
            numberOfBeams = (extraMiddleBeams * 2) + Math.floor(length / 310) + Math.floor(length / 310) + 4;
        }

        return (int) numberOfBeams;
    }

    public int calculatePriceOfBeams(int carportLength, int carportWidth, int price) {
        return (calculateNumberOfBeams(carportLength, carportWidth) * price);
    }
}
