package com.backend.fog.logics;

public class Calculator {

    public int calculatePriceOfBeams(int carportLength, int mPrice) {
        return (calculateNumberOfBeams(carportLength)) * (mPrice * 3);
    }

    public int calculateNumberOfBeams(int length) {
        double numberOfBeams = 0;
        // max 310 cm between beams
        if (length <= 310) {
            numberOfBeams = 4;
        } else {
            // 311 - 620 (6 beams)
            numberOfBeams = Math.floor(length / 310) + Math.floor(length / 310) + 4;

            // 621 - 930 (8 beams)
            // 931 - 1240 (10 beams)
        }
        return (int) numberOfBeams;
    }
}
