package com.backend.fog.facades;

import com.backend.fog.entities.Product;
import com.backend.fog.mappers.ProductMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class ProductFacade {
    DatabaseConnection databaseConnection;
    ProductMapper productMapper = new ProductMapper();

    public ProductFacade(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Product getPole() {
        return productMapper.getPole(databaseConnection);
    }
    public Product getBeam(int length) {
        return productMapper.getBeam(length, databaseConnection);
    }
    public Product getRafter(int carportWidth) {
        return productMapper.getRafter(carportWidth, databaseConnection);
    }
    public Product getLowerSideFascia(int carportLength) {
        return productMapper.getLowerSideFascia(carportLength, databaseConnection);
    }
    public Product getUpperSideFascia(int carportLength) {
        return productMapper.getUpperSideFascia(carportLength, databaseConnection);
    }
    public Product getLowerFrontBackFascia(int carportWidth) {
        return productMapper.getLowerFrontBackFascia(carportWidth, databaseConnection);
    }
    public Product getUpperFrontBackFascia(int carportWidth) {
        return productMapper.getUpperFrontBackFascia(carportWidth, databaseConnection);
    }
    public Product getRoof(int carportLength) {
        return productMapper.getRoof(carportLength, databaseConnection);
    }
    public Product getCarriageBolt() {
        return productMapper.getCarriageBolt(databaseConnection);
    }
    public Product getSquareWasher() {
        return productMapper.getSquareWasher(databaseConnection);
    }
    public Product getWindBracingStrap() {
        return productMapper.getWindBracingStrap(databaseConnection);
    }
    public Product getRafterConnectorLeft() {
        return productMapper.getRafterConnectorLeft(databaseConnection);
    }
    public Product getRafterConnectorRight() {
        return productMapper.getRafterConnectorRight(databaseConnection);
    }
    public Product getConnectorScrews() {
        return productMapper.getConnectorScrews(databaseConnection);
    }
    public Product getFasciaScrews() {
        return productMapper.getFasciaScrews(databaseConnection);
    }
    public Product getTrapezoidScrews() {
        return productMapper.getTrapezoidScrews(databaseConnection);
    }

    // SHED
    public Product getShedDoorZ() {
        return productMapper.getShedDoorZ(databaseConnection);
    }
    public Product getBeamForShed(int shedLength) {
        return productMapper.getBeamForShed(shedLength, databaseConnection);
    }
    public Product getPoleForShed() {
        return productMapper.getPoleForShed(databaseConnection);
    }
    public Product getShedJoistSide(int shedLength) {
        return productMapper.getShedJoistSide(shedLength, databaseConnection);
    }
    public Product getShedJoistFrontBack(int carportWidth) {
        return productMapper.getShedJoistFrontBack(carportWidth, databaseConnection);
    }
    public Product getCladding() {
        return productMapper.getCladding(databaseConnection);
    }
    public Product getShortCladdingScrews() {
        return productMapper.getShortCladdingScrews(databaseConnection);
    }
    public Product getLongCladdingScrews() {
        return productMapper.getLongCladdingScrews(databaseConnection);
    }
    public Product getDoorHinge() {
        return productMapper.getDoorHinge(databaseConnection);
    }
    public Product getDoorHandle() {
        return productMapper.getDoorHandle(databaseConnection);
    }
    public Product getJoistHinge() {
        return productMapper.getJoistHinge(databaseConnection);
    }
}
