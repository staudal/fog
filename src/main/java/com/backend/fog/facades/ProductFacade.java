package com.backend.fog.facades;

import com.backend.fog.entities.Product;
import com.backend.fog.mappers.ProductMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class ProductFacade {
    DatabaseConnection connection = new DatabaseConnection();
    ProductMapper productMapper = new ProductMapper();

    public Product getPole() {
        return productMapper.getPole(connection);
    }
    public Product getBeam(int length) {
        return productMapper.getBeam(length, connection);
    }
    public Product getRafter(int carportWidth) {
        return productMapper.getRafter(carportWidth, connection);
    }
    public Product getLowerSideFascia(int carportLength) {
        return productMapper.getLowerSideFascia(carportLength, connection);
    }
    public Product getUpperSideFascia(int carportLength) {
        return productMapper.getUpperSideFascia(carportLength, connection);
    }
    public Product getLowerFrontBackFascia(int carportWidth) {
        return productMapper.getLowerFrontBackFascia(carportWidth, connection);
    }
    public Product getUpperFrontBackFascia(int carportWidth) {
        return productMapper.getUpperFrontBackFascia(carportWidth, connection);
    }
    public Product getRoof(int carportLength) {
        return productMapper.getRoof(carportLength, connection);
    }
    public Product getCarriageBolt() {
        return productMapper.getCarriageBolt(connection);
    }
    public Product getSquareWasher() {
        return productMapper.getSquareWasher(connection);
    }
    public Product getWindBracingStrap() {
        return productMapper.getWindBracingStrap(connection);
    }
    public Product getRafterConnectorLeft() {
        return productMapper.getRafterConnectorLeft(connection);
    }
    public Product getRafterConnectorRight() {
        return productMapper.getRafterConnectorRight(connection);
    }
    public Product getConnectorScrews() {
        return productMapper.getConnectorScrews(connection);
    }
    public Product getFasciaScrews() {
        return productMapper.getFasciaScrews(connection);
    }
    public Product getTrapezoidScrews() {
        return productMapper.getTrapezoidScrews(connection);
    }

    // SHED
    public Product getShedDoorZ() {
        return productMapper.getShedDoorZ(connection);
    }
    public Product getBeamForShed(int shedLength) {
        return productMapper.getBeamForShed(shedLength, connection);
    }
    public Product getPoleForShed(int shedWidth) {
        return productMapper.getPoleForShed(shedWidth, connection);
    }
    public Product getShedJoistSide(int shedLength) {
        return productMapper.getShedJoistSide(shedLength, connection);
    }
    public Product getShedJoistFrontBack(int carportWidth) {
        return productMapper.getShedJoistFrontBack(carportWidth, connection);
    }
    public Product getCladding() {
        return productMapper.getCladding(connection);
    }
    public Product getShortCladdingScrews() {
        return productMapper.getShortCladdingScrews(connection);
    }
    public Product getLongCladdingScrews() {
        return productMapper.getLongCladdingScrews(connection);
    }
    public Product getDoorHinge() {
        return productMapper.getDoorHinge(connection);
    }
    public Product getDoorHandle() {
        return productMapper.getDoorHandle(connection);
    }
    public Product getJoistHinge() {
        return productMapper.getJoistHinge(connection);
    }
}
