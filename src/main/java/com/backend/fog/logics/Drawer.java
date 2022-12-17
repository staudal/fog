package com.backend.fog.logics;

import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.services.SVG;

import java.util.ArrayList;

public class Drawer {
    private ArrayList<Product> woodsCarport;
    private ArrayList<Product> screwsCarport;
    private ArrayList<Product> woodsShed;
    private ArrayList<Product> screwsShed;
    private OrderFacade orderFacade;
    private int carportLength;
    private int carportWidth;
    private int shedLength;
    private int shedWidth;

    public Drawer(ArrayList<Product> woodsCarport, ArrayList<Product> screwsCarport, ArrayList<Product> woodsShed, ArrayList<Product> screwsShed, OrderFacade orderFacade, int carportLength, int carportWidth, int shedLength, int shedWidth) {
        this.woodsCarport = woodsCarport;
        this.screwsCarport = screwsCarport;
        this.woodsShed = woodsShed;
        this.screwsShed = screwsShed;
        this.orderFacade = orderFacade;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public void drawPoles(SVG svg) {
        // pole details
        Product pole = orderFacade.getPole(woodsCarport);
        int poleQuantity = pole.getQuantity();
        double poleSize = 9.7;

        // pole position details
        double leftX = 100;
        double rightX = carportLength - 30;
        double upperY = 35 - (poleSize / 4);
        double lowerY = (carportWidth - 35 + (poleSize / 4));
        double middleX = (carportLength / 2) + (100 - 30) / 2.0;
        double middleLeftX = ((carportLength - 100 - 30) / 3) + leftX;
        double middleRightX = carportLength - ((carportLength - 100 - 30) / 3) - 30;

        // carport length affected: 0 - 440 cm
        if (poleQuantity == 4) {
            svg.addPole(leftX, upperY); // left upper pole
            svg.addPole(rightX, upperY); // right upper pole
            svg.addPole(leftX, lowerY); // left lower pole
            svg.addPole(rightX, lowerY); // right lower pole
        }

        // carport length affected: 441 - 750
        if (poleQuantity == 6) {
            svg.addPole(leftX, upperY); // left upper pole
            svg.addPole(middleX, upperY); // middle upper pole
            svg.addPole(rightX, upperY); // right upper pole
            svg.addPole(leftX, lowerY); // left lower pole
            svg.addPole(middleX, lowerY); // middle lower pole
            svg.addPole(rightX, lowerY); // right lower pole
        }

        // carport length affected: 751 - 780
        if (poleQuantity == 8) {
            svg.addPole(leftX, upperY); // left upper pole
            svg.addPole(middleLeftX, upperY); // middle-left upper pole
            svg.addPole(middleRightX, upperY); // middle-right upper pole
            svg.addPole(rightX, upperY); // right upper pole
            svg.addPole(leftX, lowerY); // left lower pole
            svg.addPole(middleLeftX, lowerY); // middle-left lower pole
            svg.addPole(middleRightX, lowerY); // middle-right lower pole
            svg.addPole(rightX, lowerY); // right lower pole
        }
    }
    public void drawRafters(SVG svg) {
        Product rafter = orderFacade.getRafter(woodsCarport);
        double rafterQuantity = rafter.getQuantity() - 1;
        double rafterWidth = 4.7;
        double buffer = rafterWidth / rafterQuantity;

        for (double i = 0; i < carportLength; i += (carportLength / rafterQuantity) - buffer) {
            svg.addRafter(i, 0, carportWidth, rafterWidth);
        }
    }
    public void drawBeams(SVG svg) {
        Product beam = orderFacade.getBeam(woodsCarport);
        double beamQuantity = beam.getQuantity();
        double beamWidth = 4.7;
        double upperY = 35;
        double lowerY = carportWidth - (35 - beamWidth);

        // carport length affected: 0 - 720
        if (beamQuantity == 2) {
            svg.addBeam(0, upperY, carportLength);
            svg.addBeam(0, lowerY, carportLength);
        }

        // carport length affected: 721 - 780
        if (beamQuantity == 4) {
            svg.addBeam(0, upperY, carportLength / 2);
            svg.addBeam(carportLength / 2, upperY, carportLength);
            svg.addBeam(0, lowerY, carportLength / 2);
            svg.addBeam(carportLength / 2, lowerY, carportLength);
        }
    }
    public void drawWindBracers(SVG svg) {
        double poleSize = 9.7;
        double x1 = 100 + (poleSize / 2);
        double y1 = 35 + (poleSize / 4);
        double x2 = carportLength - 30 + (poleSize / 2);
        double y2 = carportWidth - 35 + (poleSize / 4) * 3;
        svg.addWindBracer(x1, y1, x2, y2);
        svg.addWindBracer(x2, y1, x1, y2);
    }
    public void drawShedPoles(SVG svg, boolean fullSizeShed) {
        double poleSize = 9.7;
        double leftX = carportLength - shedLength - 30;
        double rightX = carportLength - 30;
        double lowerY = carportWidth - 35 + (poleSize / 4);
        double middleY = carportWidth - 35 - (shedWidth / 2) - (poleSize / 4);
        double upperY = carportWidth - 35 - shedWidth - (poleSize / 4);

        if (fullSizeShed) {
            svg.addShedPole(leftX, upperY); // upper shed pole
            svg.addShedPole(leftX, middleY); // middle-left shed pole
            svg.addShedPole(leftX, lowerY); // lower shed pole
            svg.addShedPole(rightX, middleY); // middle-right shed pole
        } else {
            svg.addShedPole(leftX, upperY); // upper left shed pole
            svg.addShedPole(leftX, lowerY); // lower left shed pole
            svg.addShedPole(rightX, upperY); // upper right shed pole
        }
    }
    public void drawShedBackground(SVG svg) {
        double poleSize = 9.7;
        double leftX = carportLength - shedLength - 30;
        double upperY = carportWidth - 35 - shedWidth - (poleSize / 4);
        svg.addShedBackground(leftX, upperY, shedLength + poleSize, shedWidth + (poleSize * 1.5));
    }
}
