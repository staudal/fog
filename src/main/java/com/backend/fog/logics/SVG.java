package com.backend.fog.logics;

public class SVG {
    private int x;
    private int y;
    private int height;
    private int width;
    private String viewbox;
    private StringBuilder svgString = new StringBuilder();
    private final static String HEADER_TEMPLATE = "<svg x=\"%d\" y=\"%d\" height=\"%d%%\" width=\"%d%%\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final static String RECT_TEMPLATE = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill:#ffffff\">";

    public SVG(int x, int y, int height, int width, String viewbox) {
        svgString.append(String.format(HEADER_TEMPLATE, x, y, height, width, viewbox));
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewbox = viewbox;
    }

    public void addRect(int x, int y, double height, double width) {
        svgString.append(String.format(RECT_TEMPLATE, x, y, height, width));
    }

    public void addInnerSVG(SVG innerSVGDrawing) {
        svgString.append(innerSVGDrawing);
    }

    @Override
    public String toString() {
        return svgString + "</svg>";
    }
}
