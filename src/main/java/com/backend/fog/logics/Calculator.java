package com.backend.fog.logics;

import com.backend.fog.entities.Product;
import com.backend.fog.facades.ProductFacade;
import com.backend.fog.persistence.DatabaseConnection;

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

    public int calculateNumberOfSupportBeam(int length, int width) {
        DatabaseConnection connection = new DatabaseConnection();
        ProductFacade productFacade = new ProductFacade();
        Product longestSupportBeam = productFacade.getLongestSupportBeam(connection);

        double numberOfSupportBeams = 0;

        if (length <= longestSupportBeam.getLength() && width <= 500) {
            numberOfSupportBeams = 2;
        }

        if (length <= longestSupportBeam.getLength() && width > 500) {
            numberOfSupportBeams = Math.floor(width / 500) + 2;
        }

        if (length > longestSupportBeam.getLength() && width <= 500) {
            numberOfSupportBeams = Math.floor(length / longestSupportBeam.getLength()) + Math.floor(length / longestSupportBeam.getLength()) + 2;
        }

        if (length > longestSupportBeam.getLength() && width > 500) {
            double extraMiddleSupportBeam = Math.floor(width / 500) * (Math.floor(length / longestSupportBeam.getLength()) + 1);
            numberOfSupportBeams = extraMiddleSupportBeam + Math.floor(length / longestSupportBeam.getLength()) + Math.floor(length / longestSupportBeam.getLength()) + 2;
        }

        return (int) numberOfSupportBeams;
    }

    public int calculatePriceOfSupportBeams(int carportLength, int carportWidth, int price) {
        return (calculateNumberOfSupportBeam(carportLength, carportWidth) * price);
    }
}
