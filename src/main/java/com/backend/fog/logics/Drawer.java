package com.backend.fog.logics;

import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.services.SVG;

import java.util.ArrayList;

public class Drawer {
    private final ArrayList<Product> woodsCarport;
    private final ArrayList<Product> screwsCarport;
    private final ArrayList<Product> woodsShed;
    private final ArrayList<Product> screwsShed;
    private OrderFacade orderFacade;
    private final int carportLength;
    private final int carportWidth;
    private final int shedLength;
    private final int shedWidth;

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
        double upperY = 35;
        double lowerY = (carportWidth - 35) - poleSize;
        double middleX = (carportLength / 2.0) + (100 - 30) / 2.0;
        double middleLeftX = ((carportLength - 100 - 30) / 3.0) + leftX;
        double middleRightX = carportLength - ((carportLength - 100 - 30) / 3.0) - 30;

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
        double lowerY = carportWidth - (35 + beamWidth);

        // carport length affected: 0 - 720
        if (beamQuantity == 2) {
            svg.addBeam(0, upperY, carportLength);
            svg.addBeam(0, lowerY, carportLength);
        }

        // carport length affected: 721 - 780
        if (beamQuantity == 4) {
            svg.addBeam(0, upperY, carportLength / 2.0);
            svg.addBeam(carportLength / 2.0, upperY, carportLength);
            svg.addBeam(0, lowerY, carportLength / 2.0);
            svg.addBeam(carportLength / 2.0, lowerY, carportLength);
        }
    }
    public void drawWindBracers(SVG svg) {
        double poleSize = 9.7;
        double x1 = 100 + (poleSize / 2);
        double y1 = 35 + (poleSize / 4);
        double x2 = carportLength - 30 + (poleSize / 2);
        double y2 = (carportWidth - 35) - (poleSize) + (poleSize / 4) + (poleSize / 2);
        svg.addWindBracer(x1, y1, x2, y2);
        svg.addWindBracer(x2, y1, x1, y2);
    }
    public void drawShedPoles(SVG svg, boolean fullSizeShed) {
        double poleSize = 9.7;
        double leftX = carportLength - shedLength - 30 + poleSize;
        double rightX = carportLength - 30;
        double upperY = 35;
        double middleY = carportWidth - 35 - (shedWidth / 2.0) - (poleSize / 4);
        double lowerY = (carportWidth - 35) - poleSize;

        if (fullSizeShed) {
            svg.addShedPole(leftX, upperY); // upper shed pole
            svg.addShedPole(leftX, middleY); // middle-left shed pole
            svg.addShedPole(leftX, lowerY); // lower shed pole
            svg.addShedPole(rightX, middleY); // middle-right shed pole
        } else {
            lowerY = carportWidth - 35 - shedWidth - poleSize;
            svg.addShedPole(leftX, upperY); // upper left shed pole
            svg.addShedPole(leftX, lowerY); // lower left shed pole
            svg.addShedPole(rightX, lowerY); // lower right shed pole
        }
    }
    public void drawShedBackground(SVG svg) {
        double poleSize = 9.7;
        double leftX = carportLength - shedLength - 30 + poleSize;
        double upperY = 35;

        if ((carportWidth - 70) / 2 == shedWidth) {
            svg.addShedBackground(leftX, upperY, shedLength, shedWidth);
        } else {
            svg.addShedBackground(leftX, upperY, shedLength, shedWidth);
        }
    }
    public void drawCarportWidth(SVG svg) {
        svg.addMeasurementLine(40, 100, 40, 100 + carportWidth); // main line

        svg.addDecoLine(0, 99, 200 + carportLength, 99); // upper deco
        svg.addMeasurementLine(35, 99, 45 ,99); // upper measurement

        svg.addDecoLine(0, 101 + carportWidth, 200 + carportLength, 101 + carportWidth); // lower deco
        svg.addMeasurementLine(35, 101 + carportWidth, 45, 101 + carportWidth); // lower measurement

        svg.addText((200 + carportWidth) / 2.0, -25, "90deg", String.format("%s cm", carportWidth)); // text
    }
    public void drawShedWidth(SVG svg) {
        int overlap = 35;
        svg.addMeasurementLine(80, 100 + overlap, 80, 100 + carportWidth - overlap); // main line

        svg.addDecoLine(0, 99 + overlap, 200 + carportLength, 99 + overlap); // upper deco
        svg.addMeasurementLine(75, 99 + overlap, 85 ,99 + overlap); // upper measurement

        svg.addDecoLine(0, 101 + carportWidth - overlap, 200 + carportLength, 101 + carportWidth - overlap); // lower deco
        svg.addMeasurementLine(75, 101 + carportWidth - overlap, 85, 101 + carportWidth - overlap); // lower measurement

        svg.addText((200 + carportWidth) / 2.0, -65, "90deg", String.format("%s cm", carportWidth - 70)); // text

        if ((carportWidth - 70) / 2 == shedWidth) {
            double leftX = 100 + carportLength - 30 - shedLength + 9.7;
            double x = 100 + carportLength + 15;
            double y1 = 100 + 35;
            double y2 = y1 + shedWidth;
            svg.addMeasurementLine(x, y1, x, y2);
            svg.addDecoLine(0, y1 - 1, 200 + carportLength, y1 - 1);
            svg.addDecoLine(leftX, y2 + 1, 200 + carportLength, y2 + 1);
            svg.addMeasurementLine(x - 5, y1 - 1, x + 5, y1 - 1);
            svg.addMeasurementLine(x - 5, y2 + 1, x + 5, y2 + 1);
            svg.addText(-((y1 + y2) / 2), x + 15, "270deg", String.format("%s cm", shedWidth));
        }
    }
    public void drawCarportLength(SVG svg) {
        svg.addMeasurementLine(100, 120 + carportWidth, 100 + carportLength, 120 + carportWidth); // main line

        svg.addDecoLine(99, 0, 99, 200 + carportWidth); // left deco
        svg.addMeasurementLine(99, 115 + carportWidth, 99, 125 + carportWidth);

        svg.addDecoLine(101 + carportLength, 0, 101 + carportLength, 200 + carportWidth);
        svg.addMeasurementLine(101 + carportLength, 115 + carportWidth, 101 + carportLength, 125 + carportWidth);

        svg.addText((200 + carportLength) / 2.0, 135 + carportWidth, "0", String.format("%s cm", carportLength));

//        drawer.drawMeasurementLine(svg, 100, 120 + order.getCarportWidth(), order.getCarportLength() + 100, 120 + order.getCarportWidth()); // main line
//        drawer.drawMeasurementLine(svg, 101, 115 + order.getCarportWidth(), 101, 125 + order.getCarportWidth()); // left deco
//        drawer.drawMeasurementLine(svg, 99 + order.getCarportLength(), 115 + order.getCarportWidth(), 99 + order.getCarportLength(), 125 + order.getCarportWidth()); // right deco
//        drawer.drawText(svg, (200 + order.getCarportLength()) / 2.0, (140 + order.getCarportWidth()), "0", String.format("%s cm", order.getCarportLength()));
    }
    public void drawShedLength(SVG svg) {
        double poleSize = 9.7;
        double leftX = 100 + carportLength - 30 - shedLength + poleSize;
        double rightX = 100 + carportLength - 30 + poleSize;
        svg.addMeasurementLine(leftX, 80, rightX, 80); // main line

        svg.addDecoLine(leftX - 1, 0, leftX - 1, 200 + carportWidth);
        svg.addMeasurementLine(leftX - 1, 75, leftX - 1, 85); // left measurement

        svg.addDecoLine(rightX + 1, 0, rightX + 1, 200 + carportWidth);
        svg.addMeasurementLine(rightX + 1, 75, rightX + 1, 85);

        svg.addText((rightX + leftX) / 2, 65, "0", String.format("%s cm", (int)(rightX - leftX)));
    }
    public void drawRafterWidth(SVG svg) {
        Product rafter = orderFacade.getRafter(woodsCarport);
        double rafterQuantity = rafter.getQuantity() - 1;
        double rafterWidth = 4.7;
        double buffer = rafterWidth / rafterQuantity;
        double rafterSpacing = (carportLength / rafterQuantity) - buffer - rafterWidth;
        double rafterSpacingText = Math.round(rafterSpacing * 100.0) / 100.0;

        double leftX = 100 + rafterWidth * 2 + rafterSpacing;
        double rightX = leftX + rafterSpacing;
        double y = 80;

        svg.addMeasurementLine(leftX, y, rightX, y); // main line
        svg.addDecoLine(leftX + 1, 0, leftX + 1, 200 + carportWidth);
        svg.addDecoLine(rightX - 1, 0, rightX - 1, 200 + carportWidth);
        svg.addMeasurementLine(leftX + 1, y - 5, leftX + 1, y + 5);
        svg.addMeasurementLine(rightX - 1, y - 5, rightX - 1, y + 5);

        svg.addText((rightX + leftX) / 2, 65, "0", String.format("%s cm", rafterSpacingText));
    }

    public SVG createCarportDrawing() {
        String viewbox = String.format("%d %d %d %d", 0, 0, carportLength + 200, carportWidth + 200);
        String innerViewbox = String.format("%d %d %d %d", 0, 0, carportLength, carportWidth);

        SVG svg = new SVG(0, 0, carportWidth + 200, carportLength + 200, viewbox);
        SVG innerSVG = new SVG(100, 100, carportWidth, carportLength, innerViewbox);

        boolean fullSizeShed = carportWidth - 70 == shedWidth;
        boolean halfSizeShed = (carportWidth - 70) / 2 == shedWidth;

        // no shed
        if (shedWidth == 0) {
            // the carport parts
            drawWindBracers(innerSVG);
            drawPoles(innerSVG);
            drawBeams(innerSVG);
            drawRafters(innerSVG);
            // the measurement parts
            drawCarportLength(svg);
            drawCarportWidth(svg);
            drawRafterWidth(svg);
            drawShedWidth(svg);
        }

        // half-size shed
        if (halfSizeShed) {
            // the shed parts
            drawShedBackground(innerSVG);
            drawShedPoles(innerSVG, false);
            // the carport parts
            drawWindBracers(innerSVG);
            drawPoles(innerSVG);
            drawBeams(innerSVG);
            drawRafters(innerSVG);
            // the measurement parts
            drawCarportLength(svg);
            drawCarportWidth(svg);
            drawRafterWidth(svg);
            drawShedWidth(svg);
            drawShedLength(svg);
        }

        // full-size shed
        if (fullSizeShed) {
            // the shed parts
            drawShedBackground(innerSVG);
            drawShedPoles(innerSVG, true);
            // the carport parts
            drawWindBracers(innerSVG);
            drawPoles(innerSVG);
            drawBeams(innerSVG);
            drawRafters(innerSVG);
            // the measurement parts
            drawCarportLength(svg);
            drawCarportWidth(svg);
            drawRafterWidth(svg);
            drawShedWidth(svg);
            drawShedLength(svg);
        }

        svg.addInnerSVG(innerSVG);
        return svg;
    }
}
