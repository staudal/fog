package com.backend.fog.facades;

import com.backend.fog.entities.Product;
import com.backend.fog.mappers.ProductMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class ProductFacade {
    DatabaseConnection connection = new DatabaseConnection();
    ProductMapper productMapper = new ProductMapper();

    public Product getPole(int carportHeight) {
        return productMapper.getPole(carportHeight, connection);
    }

    public Product getSupportBeamCloseTo(int length) {
        return productMapper.getSupportBeamCloseTo(length, connection);
    }

    public Product getSupportBeam(int carportLength) {
        return productMapper.getSupportBeam(carportLength, connection);
    }

    public Product getRafter(int carportWidth) {
        return productMapper.getRafter(carportWidth, connection);
    }

    public Product getFrontBackUpperFascia(int carportWidth) {
        return productMapper.getFrontBackUpperFascia(carportWidth, connection);
    }

    public Product getFrontBackLowerFascia(int carportWidth) {
        return productMapper.getFrontBackLowerFascia(carportWidth, connection);
    }

    public Product getSideUpperFascia(int carportLength) {
        return productMapper.getSideUpperFascia(carportLength, connection);
    }

    public Product getSideLowerFascia(int carportLength) {
        return productMapper.getSideLowerFascia(carportLength, connection);
    }
}
