package com.backend.fog.services;

public class SVG {
    private int x;
    private int y;
    private int height;
    private int width;
    private String viewbox;
    private StringBuilder svgString = new StringBuilder();
    private final static String HEADER_TEMPLATE = "<svg x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final static String RAFTER_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: #000; fill: #52A384; stroke-width: 1\" />";
    private final static String POLE_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: #000000; fill: #000; stroke-width: 1\" />";
    private final static String SHED_POLE_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: #000; fill: #767676; stroke-width: 1\" />";
    private final static String BEAM_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: #000; fill: #63462D; stroke-width: 1\" />";
    private final static String WIND_BRACER_TEMPLATE = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke: #ccc; stroke-width: 4; stroke-dasharray: 8;\" />";
    private final static String SHED_BACKGROUND_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: #a9a9a9; fill: #a9a9a9; stroke-width: 1\" />";

    public SVG(int x, int y, int height, int width, String viewbox) {
        svgString.append(String.format(HEADER_TEMPLATE, x, y, height, width, viewbox));
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewbox = viewbox;
    }

    public void addRafter(double x, double y, double height, double width) {
        svgString.append(String.format(RAFTER_TEMPLATE, x, y, height, width));
    }

    public void addPole(double x, double y) {
        double poleSize = 9.7;
        svgString.append(String.format(POLE_TEMPLATE, x, y, poleSize, poleSize));
    }

    public void addBeam(double x, double y, double beamLength) {
        double beamWidth = 4.7;
        svgString.append(String.format(BEAM_TEMPLATE, x, y, beamWidth, beamLength));
    }

    public void addWindBracer(double x1, double y1, double x2, double y2) {
        svgString.append(String.format(WIND_BRACER_TEMPLATE, x1, y1, x2, y2));
    }

    public void addShedPole(double x, double y) {
        double poleSize = 9.7;
        svgString.append(String.format(SHED_POLE_TEMPLATE, x, y, poleSize, poleSize));
    }

    public void addShedBackground(double x, double y, double width, double height) {
        svgString.append(String.format(SHED_BACKGROUND_TEMPLATE, x, y, height, width));
    }

    @Override
    public String toString() {
        return svgString + "</svg>";
    }
}
